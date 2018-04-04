package com.base.servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.anno.Autowired;
import com.base.anno.Controller;
import com.base.anno.RequestMapping;
import com.base.anno.Service;
import com.base.xml.XmlParse;



public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> packageNames = new ArrayList<>();
	private Map<String,Object> classMap = new HashMap<String,Object>();
	private Map<String,Object> methodMap = new HashMap<String,Object>();   
 
    public DispatcherServlet() {
        super();
    }

	
	public void init(ServletConfig config) throws ServletException {
		XmlParse xmlParse = new XmlParse();
		Map<String, Object> configs = xmlParse.getConfig(config.getInitParameter("configxml"));
		scanPackage(configs.get("scanPackage").toString());
		try {
			saveClass();
		} catch (Exception e) {
			e.printStackTrace();
		}
		handlerMap();
		ioc();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> enums= request.getParameterNames();
		String mName ="";
		while (enums.hasMoreElements()){
			mName =enums.nextElement();
			if(!mName.equals("")){
				break;
			}
		}
		String reuestUrl = request.getRequestURI();
		String pName = request.getContextPath();
		String realUrl = reuestUrl.replaceAll(pName, "");
		String cName = (realUrl.split("/")[1]);
		Method method =(Method) methodMap.get("/"+cName.split("\\.")[0]+"/"+mName);
		Object controller =  classMap.get(cName.split("\\.")[0]);
		try {
			method.invoke(controller, new Object[]{request,response});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	private void scanPackage(String packageName){
		String packageName1 = replacePackageName(packageName);
		URL url = this.getClass().getClassLoader().getResource(packageName1);	
		String pathFile= url.getFile();
		File file = new File(pathFile);
		String[] files = file.list();
		for(String filePath : files){
			File eachFile = new File(pathFile+"/"+filePath);
			if(eachFile.isDirectory()){
				scanPackage(packageName+"."+eachFile.getName());
			}else{
				if(getFileExtendName(eachFile.getName()).equals("class"))
					packageNames.add(packageName+"."+eachFile.getName());
				continue;
			}
		}
	}
	

	private String replacePackageName(String packageName){
		return packageName.replaceAll("\\.", "/");
	}
	
	
	private void saveClass() throws Exception{

		if(packageNames.size() <=0){
			return;
		}
		for(String pkn:packageNames){

			Class<?> object = Class.forName(pkn.replace(".class", "").trim());

			if(object.isAnnotationPresent(Controller.class)){

				Object instance = object.newInstance();

				Controller controller = object.getAnnotation(Controller.class);

				String key = controller.value();

				if(key.equals("")){

					key=object.getSimpleName().substring(0, 1).toLowerCase()+object.getSimpleName().substring(1);
				}

				classMap.put(key, instance);

			}else if(object.isAnnotationPresent(Service.class)){

				Object instance = object.newInstance();

				Service service = object.getAnnotation(Service.class);

				String key = service.value();

				if(key.equals("")){

					key=object.getSimpleName().substring(0, 1).toLowerCase()+object.getSimpleName().substring(1);
				}

				classMap.put(key, instance);
			}else{
				continue;
			}
		}		
	}
	
	private void handlerMap(){

		if(classMap.size() <=0){
			return;
		}

		for (Map.Entry<String,Object> entry : classMap.entrySet()) {

			if(entry.getValue().getClass().isAnnotationPresent(Controller.class)){

				Controller controller = entry.getValue().getClass().getAnnotation(Controller.class);

				String value = controller.value();

				if(value.equals("")){

					value=entry.getValue().getClass().getSimpleName().substring(0, 1).toLowerCase()+entry.getValue().getClass().getSimpleName().substring(1);
				}

				Method[] methods = entry.getValue().getClass().getMethods();
				for (Method method : methods) {

					if(method.isAnnotationPresent(RequestMapping.class)){

						RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);

						String rvalue = requestMapping.value();

						if(rvalue.equals("")){

							rvalue=method.getClass().getSimpleName().substring(0, 1).toLowerCase()+method.getClass().getSimpleName().substring(1);
						}

						methodMap.put("/"+value+rvalue, method);
					}else{
						continue;
					}
				}
			}else{
				continue;
			}
		}
	}
	
	private void ioc(){

		if(classMap.size() <=0){
			return;
		}

		for (Map.Entry<String, Object> entry : classMap.entrySet()) {

			Field[] fileds =entry.getValue().getClass().getDeclaredFields();
			for (Field field : fileds) {

				field.setAccessible(true);

				if(field.isAnnotationPresent(Autowired.class)){
					try {

						field.setAccessible(true);

						String packString= field.getType().getPackage().getName();

						String className=  field.getType().getSimpleName()+"Impl";

						Class<?> obj = Class.forName(packString+".impl."+className);
						String value="";

						if(obj.isAnnotationPresent(Controller.class)){

							value= obj.getAnnotation(Controller.class).value();
							if(value.equals("")){

								value=obj.getSimpleName().substring(0, 1).toLowerCase()+obj.getSimpleName().substring(1);
							}

						}else if(obj.isAnnotationPresent(Service.class)){

							value= obj.getAnnotation(Service.class).value();
							if(value.equals("")){

								value=obj.getSimpleName().substring(0, 1).toLowerCase()+obj.getSimpleName().substring(1);
							}
						}

						field.set(entry.getValue(), classMap.get(value));
					} catch (IllegalArgumentException | IllegalAccessException | ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	
	private String getFileExtendName(String fileName){

		if(fileName == null ||fileName.length() <=0)
			return null;

		int lastIndex = fileName.lastIndexOf(".");

		if(lastIndex > -1 && lastIndex <(fileName.length()-1))

			return fileName.substring(lastIndex+1);
		return fileName;
	}
}


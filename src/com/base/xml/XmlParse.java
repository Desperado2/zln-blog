package com.base.xml;

import com.base.util.MapAndJavaBeanCovertUtils;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;


public class XmlParse {


	public Map<String,Object> getConfig(){
		Map<String,Object> map = new HashMap<String, Object>();
		try {

			InputStream inputStream = this.getClass().getClassLoader().getResource("minimvc.xml").openStream();

			JAXBContext jaxbContext = JAXBContext.newInstance(Beans.class);

			Unmarshaller unmarshaller =jaxbContext.createUnmarshaller();
			Beans bean= (Beans) unmarshaller.unmarshal(inputStream);
			map = MapAndJavaBeanCovertUtils.objectToMap(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * �����Զ����xml�ļ����ƻ�ȡ����
	 * @param xmlName  �����ļ�������
	 * @return
	 */
	public Map<String,Object> getConfig(String xmlName){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			//��ȡxml�����ļ�������inputstream
			InputStream inputStream = this.getClass().getClassLoader().getResource(xmlName).openStream();
			//����xml����Ķ����class����JASB
			JAXBContext jaxbContext = JAXBContext.newInstance(Beans.class);
			//����xml
			Unmarshaller unmarshaller =jaxbContext.createUnmarshaller();
			Beans bean= (Beans) unmarshaller.unmarshal(inputStream);
			map = MapAndJavaBeanCovertUtils.objectToMap(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
}

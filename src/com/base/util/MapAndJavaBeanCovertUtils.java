package com.base.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;



public class MapAndJavaBeanCovertUtils {


	public static  Map<String, Object> objectToMap(Object object) throws Exception{
		 Map<String,Object> map = new HashMap<String,Object>();
		 if(object == null)
			 return null;
		 Field[] fields = object.getClass().getDeclaredFields();
		 for (Field field : fields) {

			 field.setAccessible(true);

			 String name = field.getName();

			 Object value = field.get(object);
			 map.put(name, value);
		}
		return map;	
	}
	

	public static Object mapToObject(Map<String, Object> map,Class<?> clazz) throws Exception{
		if(map.size() <=0)
			return null;

		Object object = clazz.newInstance();

		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {

			int mod = field.getModifiers();

			if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
				continue;
			}

			field.setAccessible(true);

			field.set(object, map.get(field.getName()));
		}
		
		return object;
	}
}

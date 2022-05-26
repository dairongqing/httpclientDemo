package com.pengtu.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ObjectToMapUtil {
	public static Map<String, Object> ObjectToMap(Object obj) {
		 if(obj == null){    
	            return null;    
	        }   
	  
	        Map<String, Object> map = new HashMap<String, Object>();    
	  
	        Field[] declaredFields = obj.getClass().getDeclaredFields();    
	        for (Field field : declaredFields) {    
	            field.setAccessible(true);  
	            try {
	            	Object object = null;
	            	if (field.get(obj) == null) {
	            		object = "";
					}else {
						object = field.get(obj);
						if(object instanceof Date) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							object = sdf.format((Date)object);
						}
					}
					map.put(field.getName(), object);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
	        }    
	  
	        return map;  
		
	}
}

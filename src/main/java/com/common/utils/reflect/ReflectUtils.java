package com.common.utils.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class ReflectUtils 
{
	public static Map<String, Object> objectToMap(Object object)
	{
		Method[] methods = object.getClass().getDeclaredMethods();
		Map<String, Object> map = new HashMap<String, Object>();
		for(Method method : methods)
		{
			if (method.getName().startsWith("get"))
			{
				try 
				{
					Object arg = method.invoke(object);
					String name = method.getName().substring(3).toLowerCase();
					map.put(name, arg);
				} 
				catch (IllegalAccessException e) 
				{
					e.printStackTrace();
				} 
				catch (IllegalArgumentException e) 
				{
					e.printStackTrace();
				} 
				catch (InvocationTargetException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		return map;
	}
	
	public static void mapToObject(Map<String, Object> map, Object object)
	{
		Method[] methods = object.getClass().getDeclaredMethods();
		for(Method method : methods)
		{
			if (method.getName().startsWith("set"))
			{
				String name = method.getName().substring(3).toLowerCase();
				if (map.containsKey(name))
				{
					Object value = map.get(name);
					try 
					{
						method.invoke(object, value);
					}
					catch (IllegalAccessException e) 
					{
						e.printStackTrace();
					} 
					catch (IllegalArgumentException e) 
					{
						e.printStackTrace();
					} 
					catch (InvocationTargetException e) 
					{
						e.printStackTrace();
					}
				}
			}
		}
	}
	
}

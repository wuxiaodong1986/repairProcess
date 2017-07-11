package com.common.utils.cache;

import java.util.List;
import java.util.Map;

import com.college.service.repairProcess.CollegeProcessConfigCenter;

public class CacheUtils 
{
	public static void changeConfigValue(Object configMapMapObject, List<Map<String, Object>> objects, String code, String change)
	{
		@SuppressWarnings("unchecked")
		Map<String, Map<String, String>> configMapMap = (Map<String, Map<String, String>>)configMapMapObject;
		
		Map<String, String> configMap = configMapMap.get(code);
		if (null == configMap) 
		{
			return;
		}	
		
		for (Map<String, Object> object : objects)
		{
			Object from = object.get(change);
			if (null == from) 
			{
				continue;
			}
			
			String to = configMap.get(String.valueOf(from));
			if (null == to)
			{
				continue;
			}
			
			object.put(change, to);
		}
	}
	
	public static void changeProcessConfigValue(Object processConfigNameMap, List<Map<String, Object>> objects, String change)
	{
		@SuppressWarnings("unchecked")
		Map<Integer, String> configMapMap = (Map<Integer, String>)processConfigNameMap;
		
		for (Map<String, Object> object : objects)
		{
			Object from = object.get(change);
			if (null == from) 
			{
				continue;
			}
			
			String to = configMapMap.get(Integer.valueOf(from.toString()));
			if (null == to)
			{
				continue;
			}
			
			object.put(change, to);
		}
	}
	
	public static void changeProcessConfigValue(CollegeProcessConfigCenter collegeProcessConfigCenter, Object processConfigNameMap, List<Map<String, Object>> objects, String change)
	{
		@SuppressWarnings("unchecked")
		Map<Integer, String> configMapMap = (Map<Integer, String>)processConfigNameMap;
		
		for (Map<String, Object> object : objects)
		{
			Object from = object.get(change);
			if (null == from) 
			{
				continue;
			}
			
			int id = Integer.valueOf(from.toString());
			int next = collegeProcessConfigCenter.getNextStaffProcessConfig(id).getId();
					
			String to = configMapMap.get(next);
			if (null == to)
			{
				continue;
			}
			
			if (!"Íê³É".equals(to))
			{
				to = "µÈ´ý" + to;
			}
			
			object.put(change, to);
		}
	}
}

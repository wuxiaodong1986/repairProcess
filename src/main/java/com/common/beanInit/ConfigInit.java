package com.common.beanInit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.common.dao.ConfigDao;


@Configuration
public class ConfigInit 
{
	@Autowired
	private ConfigDao configDao;
	
	@Bean(name="configMapMap")
	public Map<String, Map<String, String>> getConfigMap()
	{
		Map<String, Map<String, String>> configMap = new HashMap<String, Map<String, String>>();
		
		List<Map<String, Object>> configs = configDao.getConfigs();
		
		for (Map<String, Object> config : configs)
		{
			Map<String, String> map = null;
			if ((map = configMap.get(config.get("code"))) == null)
			{
				map = new HashMap<String, String>();
				configMap.put((String)config.get("code"), map);
			}
			map.put((String)config.get("cfg_key"), (String)config.get("cfg_value"));
		}
		
		return configMap;
	}
	
	@Bean(name="configListMap")
	public Map<String, List<Map<String, Object>>> getConfigList()
	{
		Map<String, List<Map<String, Object>>> configMap = new HashMap<String, List<Map<String, Object>>>();
		
		List<Map<String, Object>> configs = configDao.getConfigs();
		
		for (Map<String, Object> config : configs)
		{
			List<Map<String, Object>> list = null;
			
			if ((list = configMap.get(config.get("code"))) == null)
			{
				list = new ArrayList<Map<String, Object>>();
				configMap.put((String)config.get("code"), list);
			}
			list.add(config);
		}
		
		return configMap;
	}
}

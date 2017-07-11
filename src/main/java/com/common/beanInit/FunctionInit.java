package com.common.beanInit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.common.dao.FunctionDao;
import com.common.dto.Function;

@Configuration
public class FunctionInit 
{
	@Autowired
    private FunctionDao functionDao;
	
	@Bean(name="idFunctionMap")
	public Map<String, Function> getIdFunctionMap()
	{
		List<Function> functions =  functionDao.getFunctions();
		
		Map<String, Function> functionMap = new HashMap<String, Function>();
		
		for (Function node : functions)
		{
			Function function = node;
			functionMap.put(function.getId(), function);
		}
		
		return functionMap;
	}
	
	@Bean(name="urlFunctionMap")
	public Map<String, Function> getUrlFunctionMap()
	{
		List<Function> functions =  functionDao.getFunctions();
		
		Map<String, Function> functionMap = new HashMap<String, Function>();
		
		for (Function node : functions)
		{
			Function function = node;
			functionMap.put(function.getUrl(), function);
		}
		
		return functionMap;
	}
}

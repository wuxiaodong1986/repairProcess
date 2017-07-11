package com.college.beanInit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.college.dto.Operator;
import com.college.dao.OperatorDao;
import com.college.dao.OrgDao;

@Configuration
public class OperatorInit 
{
	@Autowired
	private OperatorDao operatorDao;
	
	@Autowired
	private OrgDao orgDao;
	
	@Scope("prototype")
	@Bean(name="operatorMap")
	public Map<Integer, Operator> getOperatorMap()
	{
		List<Operator> operators = operatorDao.getOperatorForCache();
		
		Map<Integer, Operator> operatorMap = new HashMap<Integer, Operator>();
		
		for (Operator operator : operators)
		{
			operatorMap.put(operator.getId(), operator);
		}
		
		return operatorMap;
	}
	
}

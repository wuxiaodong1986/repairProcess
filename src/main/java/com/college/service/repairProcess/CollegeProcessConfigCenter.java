package com.college.service.repairProcess;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.college.dto.ProcessConfig;

@Component("collegeProcessConfigCenter")
public class CollegeProcessConfigCenter 
{
	@Autowired
	@Qualifier("processConfigListMap")
	private Object processConfigListMapObject;
	
	@Autowired
	@Qualifier("processConfigMap")
	private Object processConfigMapObject;
	
	@SuppressWarnings("unchecked")
	public ProcessConfig getProcessConfig(int id)
	{
		Map<Integer, ProcessConfig> processConfigMap = (Map<Integer, ProcessConfig>)processConfigMapObject;
		ProcessConfig collegeProcessConfig = processConfigMap.get(id);
		
		return collegeProcessConfig;
	}
	
	@SuppressWarnings("unchecked")
	public ProcessConfig getNextStaffProcessConfig(int id)
	{
		Map<Integer, ProcessConfig> staffProcessConfigMap = (Map<Integer, ProcessConfig>)processConfigMapObject;
		ProcessConfig collegeProcessConfig = staffProcessConfigMap.get(id);
		
		ProcessConfig nextProcessConfig = null;
		Map<String, List<ProcessConfig>> processConfigListMap = (Map<String, List<ProcessConfig>>)processConfigListMapObject;
		List<ProcessConfig> processConfigs = processConfigListMap.get(collegeProcessConfig.getCode());
		if (processConfigs.size() > processConfigs.indexOf(collegeProcessConfig)+1)
		{
			nextProcessConfig = processConfigs.get(processConfigs.indexOf(collegeProcessConfig)+1);
		}
		else
		{
			nextProcessConfig = processConfigs.get(processConfigs.size()+1);
		}
		
		return nextProcessConfig;
	}
	
	
}

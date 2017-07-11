package com.college.beanInit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.college.dao.ProcessConfigDao;
import com.college.dto.ProcessConfig;

@Configuration
public class ProcessConfigInit 
{
	@Autowired
	private ProcessConfigDao processConfigDao;
	
	@Bean(name="collegeProcessConfigs")
	public List<ProcessConfig> getCollegeProcessConfigs()
	{
		return processConfigDao.getCollegeProcesses();
	}
	
	@Bean(name="collegeProcessConfigsMap")
	public Map<String, Map<Integer, ProcessConfig>> collegeProcessConfigsMap()
	{
		List<ProcessConfig> collegeProcesses =  processConfigDao.getCollegeProcesses();
		
		Map<String, Map<Integer, ProcessConfig>> collegeProcessConfigs = new HashMap<String, Map<Integer, ProcessConfig>>();
		Map<String, List<ProcessConfig>> collegeProcessListMap = new HashMap<String, List<ProcessConfig>>();
		
		for (ProcessConfig collegeProcess : collegeProcesses)
		{
			Map<Integer, ProcessConfig> collegeProcessConfig = null;
			List<ProcessConfig> collegeProcessList = null;
			if ((collegeProcessConfig = collegeProcessConfigs.get(collegeProcess.getCode())) == null)
			{
				collegeProcessConfig = new HashMap<Integer, ProcessConfig>();
				collegeProcessList = new ArrayList<ProcessConfig>();
				
				collegeProcessConfigs.put(collegeProcess.getCode(), collegeProcessConfig);
				collegeProcessListMap.put(collegeProcess.getCode(), collegeProcessList);
			}
			else
			{
				collegeProcessList = collegeProcessListMap.get(collegeProcess.getCode());
				if (collegeProcessList.size() != 0)
				{
					ProcessConfig before = collegeProcessList.get(collegeProcessList.size()-1);
					collegeProcessConfig.put(before.getRoleid(), collegeProcess);
				}
			}
			
			collegeProcessList.add(collegeProcess);
			
		}
		
		return collegeProcessConfigs;
	}
	
	@Bean(name="processConfigNameMap")
	public Map<Integer, String> getProcessConfigNameMap()
	{
		List<ProcessConfig> collegeProcesses =  processConfigDao.getCollegeProcesses();
		
		Map<Integer, String> processConfigNameMap = new HashMap<Integer, String>();
		
		for (ProcessConfig collegeProcess : collegeProcesses)
		{
			processConfigNameMap.put(collegeProcess.getId(), collegeProcess.getDscb());
		}
		return processConfigNameMap;
	}
}

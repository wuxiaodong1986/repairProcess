package com.college.service.repairProcess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.college.dto.ProcessConfig;
import com.common.utils.db.ObjectRowMapper;

@Configuration
public class RepairProcessBeanConf 
{
	@Autowired
	@Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
	
	@Bean(name="processConfigMap")
	public Map<Integer, ProcessConfig> processConfigMap()
	{
		String sql = "select * from college_process_config ";
		
		List<ProcessConfig> processConfigs = jdbcTemplate.query(sql, new ObjectRowMapper<ProcessConfig>(ProcessConfig.class));
		
		Map<Integer, ProcessConfig> processConfigMap = new HashMap<Integer, ProcessConfig>();
		
		for (ProcessConfig processConfig : processConfigs)
		{
			processConfigMap.put(processConfig.getId(), processConfig);
		}
		
		return processConfigMap;
	}
	
	@Bean(name="processConfigListMap")
	public Map<String, List<ProcessConfig>> processConfigListMap()
	{
		String sql = "select * from college_process_config order by list ";
		
		List<ProcessConfig> processConfigs = jdbcTemplate.query(sql, new ObjectRowMapper<ProcessConfig>(ProcessConfig.class));
		
		Map<String, List<ProcessConfig>> processConfigListMap = new HashMap<String, List<ProcessConfig>>();
		for (ProcessConfig collegeProcessConfig : processConfigs)
		{
			List<ProcessConfig> singelProcessConfigs = processConfigListMap.get(collegeProcessConfig.getCode());
			if (null == singelProcessConfigs)
			{
				singelProcessConfigs = new ArrayList<ProcessConfig>();
				processConfigListMap.put(collegeProcessConfig.getCode(), singelProcessConfigs);
			}
			singelProcessConfigs.add(collegeProcessConfig);
		}
		
		return processConfigListMap;
	}
}

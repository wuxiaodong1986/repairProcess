package com.college.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.dao.ProcessConfigDao;
import com.college.dto.ProcessConfig;

@Service
public class ProcessConfigService 
{
	@Autowired
	private ProcessConfigDao processConfigDao;
	
	public List<ProcessConfig> getCollegeProcessesByOid(int oid)
	{
		return processConfigDao.getCollegeProcessesByOid(oid);
	}
}

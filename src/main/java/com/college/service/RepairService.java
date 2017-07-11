package com.college.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.college.dao.OperatorDao;
import com.college.dao.RepairDao;
import com.college.dto.Operator;
import com.college.dto.ProcessConfig;
import com.college.dto.RepairInfo;
import com.college.service.repairProcess.CollegeProcessConfigCenter;
import com.college.service.repairProcess.intf.RepairProcess;
import com.common.utils.paging.PageUtils;

@Service
public class RepairService 
{
	@Autowired
	private RepairDao repairDao;
	
	@Autowired
	private ProcessConfigService processConfigService;
	
	@Autowired(required=false)
	private Map<String, RepairProcess> repairProcessMap;
	
	@Autowired
	@Qualifier("collegeProcessConfigCenter")
	private CollegeProcessConfigCenter collegeProcessConfigCenter;
	
	@Autowired
	private OperatorDao operatorDao;
	
	public int add(RepairInfo object)
	{
		return repairDao.add(object);
	}
	
	public int update(RepairInfo object)
	{
		return repairDao.update(object);
	}
	
	public List<Map<String, Object>> getRepairInfos(Operator operator, RepairInfo repairInfo, int pageNum)
	{
		List<ProcessConfig> processConfigs = processConfigService.getCollegeProcessesByOid(operator.getId());
		
		List<RepairProcess> repairProcesses = new ArrayList<RepairProcess>();
		
		for (ProcessConfig processConfig : processConfigs)
		{
			RepairProcess repairProcess = repairProcessMap.get(processConfig.getImplbean());
			if (null != repairProcess)
			{
				repairProcesses.add(repairProcess);
			}
		}
		
		return repairDao.getObjects(repairProcesses, operator, repairInfo, pageNum, PageUtils.pagesize);
	}
	
	public int countRepairInfos(Operator operator, RepairInfo repairInfo)
	{
		List<ProcessConfig> processConfigs = processConfigService.getCollegeProcessesByOid(operator.getId());
		
		List<RepairProcess> repairProcesses = new ArrayList<RepairProcess>();
		
		for (ProcessConfig processConfig : processConfigs)
		{
			RepairProcess repairProcess = repairProcessMap.get(processConfig.getImplbean());
			if (null != repairProcess)
			{
				repairProcesses.add(repairProcess);
			}
		}
		return repairDao.countObjects(repairProcesses, operator, repairInfo);
	}
	
	public RepairInfo getRepairInfoById(RepairInfo object)
	{
		return repairDao.getRepairInfoById(object);
	}
	
	public String getRepairInfoPageViewName(Operator operator, RepairInfo repairInfo)
	{
		ProcessConfig nextProcessConfig = collegeProcessConfigCenter.getNextStaffProcessConfig(repairInfo.getStatus());
		
		if (operatorDao.checkOperatorRole(operator.getId(), nextProcessConfig.getRoleid()))
		{
			RepairProcess repairProcess = repairProcessMap.get(nextProcessConfig.getImplbean());
			
			return repairProcess.page();
		}
		else
		{
			return "college/process/viewRepairInfo";
		}
	}
	
	public String getBeanName(Operator operator, RepairInfo repairInfo)
	{
		ProcessConfig nextProcessConfig = collegeProcessConfigCenter.getNextStaffProcessConfig(repairInfo.getStatus());
		
		if (operatorDao.checkOperatorRole(operator.getId(), nextProcessConfig.getRoleid()))
		{
			return nextProcessConfig.getImplbean();
		}
		else
		{
			return "viewProcess";
		}
	}
	
	public void next(RepairInfo repairInfo)
	{
		ProcessConfig nextProcessConfig = collegeProcessConfigCenter.getNextStaffProcessConfig(repairInfo.getStatus());
		
		repairInfo.setStatus(nextProcessConfig.getId());
		
		RepairProcess repairProcess = repairProcessMap.get(nextProcessConfig.getImplbean());
		
		repairProcess.next(repairInfo);
	}
}

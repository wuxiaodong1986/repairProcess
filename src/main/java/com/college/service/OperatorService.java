package com.college.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.dao.OperatorDao;
import com.college.dto.Operator;
import com.common.utils.paging.PageUtils;

@Service("collegeOperatorService")
public class OperatorService 
{
	@Autowired
	private OperatorDao operatorDao;
	
	public List<Map<String, Object>> getObjects(Operator object, int pageNum)
	{
		return operatorDao.getObjects(object, pageNum, PageUtils.pagesize);
	}
	
	public int countObjects(Operator object)
	{
		return operatorDao.countObjects(object);
	}
	
	public Operator getObjectById(int id)
	{
		return operatorDao.getObjectById(id);
	}
	
	public int deleteObject(Operator object)
	{
		return operatorDao.deleteObject(object);
	}
	
	public List<Map<String, Object>> getOperatorsByOrg(String staff_org_id)
	{
		return operatorDao.getOperatorsByOrg(staff_org_id);
	}
	public int addStaff(Operator object)
	{
		return operatorDao.addStaff(object);
	}
	
	public int updateStaff(Operator object)
	{
		return operatorDao.updateStaff(object);
	}
	
	public int updateStaffSelf(Operator object)
	{
		return operatorDao.updateStaffSelf(object);
	}
	
	public void changeOprIdToName(List<Map<String, Object>> objects, Map<Integer, Operator> operatorMap, String code)
	{
		for (Map<String, Object> object : objects)
		{
			Operator operator = operatorMap.get(object.get(code));
			if (null != operator)
			{
				object.put(code, operator.getName());
			}
			else
			{
				object.put(code, "");
			}
		}
	}
}

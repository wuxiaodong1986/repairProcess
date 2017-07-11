package com.college.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.dao.OrgDao;
import com.college.dto.Org;
import com.common.utils.paging.PageUtils;

@Service
public class OrgService 
{
	@Autowired
	private OrgDao dao;
	
	public List<Map<String, Object>> getObjects(Org object, int pageNum) 
	{
		return dao.getObjects(object, pageNum, PageUtils.pagesize);
	}

	public int countObjects(Org object) 
	{
		return dao.countObjects(object);
	}

	public Org getObjectById(Org object) 
	{
		return dao.getObjectById(object);
	}

	public int addObject(Org object) 
	{
		return dao.addObject(object);
	}

	public int updateObject(Org object) 
	{
		return dao.updateObject(object);
	}

	public int deleteObject(Org object) 
	{
		return dao.deleteObject(object);
	}
	
	public void changeOrgIdToName(List<Map<String, Object>> objects, Map<String, Org> collegeOrgMap, String code)
	{
		for (Map<String, Object> object : objects)
		{
			Org org = collegeOrgMap.get(object.get(code));
			if (null != org)
			{
				object.put(code, org.getName());
			}
			else
			{
				object.put(code, "");
			}
		}
	}
}

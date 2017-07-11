package com.college.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.dao.AddressDao;
import com.college.dto.Address;
import com.common.utils.paging.PageUtils;

@Service
public class AddressService 
{
	@Autowired
	private AddressDao dao;
	
	public List<Map<String, Object>> getObjects(Address object, int pageNum) 
	{
		return dao.getObjects(object, pageNum, PageUtils.pagesize);
	}

	public int countObjects(Address object) 
	{
		return dao.countObjects(object);
	}

	public Address getObjectById(Address object) 
	{
		return dao.getObjectById(object);
	}

	public int addObject(Address object) 
	{
		return dao.addObject(object);
	}

	public int updateObject(Address object) 
	{
		return dao.updateObject(object);
	}

	public int deleteObject(Address object) 
	{
		return dao.deleteObject(object);
	}
	
	public void changeAddressIdToName(List<Map<String, Object>> objects, Map<Integer, Address> collegeAddressMap, String code)
	{
		for (Map<String, Object> object : objects)
		{
			Address address = collegeAddressMap.get(object.get(code));
			if (null != address)
			{
				object.put(code, address.getDscb());
			}
			else
			{
				object.put(code, "");
			}
		}
	}
}

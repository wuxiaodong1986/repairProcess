package com.college.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.college.dao.EquipmentDao;
import com.college.dto.Equipment;
import com.common.utils.paging.PageUtils;

@Service
public class EquipmentService 
{
	@Autowired
	private EquipmentDao equipmentDao;
	
	public List<Map<String, Object>> getObjects(Equipment object, int pageNum)
	{
		return equipmentDao.getObjects(object, pageNum, PageUtils.pagesize);
	}
	
	public int countObjects(Equipment object)
	{
		return equipmentDao.countObjects(object);
	}
	
	public Equipment getObjectById(int id)
	{
		return equipmentDao.getObjectById(id);
	}
	
	public int addObject(Equipment object)
	{
		return equipmentDao.addObject(object);
	}
	
	public int updateObject(Equipment object)
	{
		return equipmentDao.updateObject(object);
	}
	
	public int deleteObject(Equipment object)
	{
		return equipmentDao.deleteObject(object);
	}
}

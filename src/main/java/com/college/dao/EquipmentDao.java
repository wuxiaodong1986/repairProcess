package com.college.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.college.dao.rowMapper.EquipmentRowMapper;
import com.college.dto.Equipment;
import com.common.utils.db.MapRowMapper;

@Repository
public class EquipmentDao 
{
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> getObjects(Equipment object, int pageNum, int pageSize)
	{
		String sql = "SELECT * FROM college_equipment  %wheresql order by id limit ? , ?";
		List<Object> objects = new ArrayList<Object>();
		sql = sql.replace("%wheresql", getWhereSql(object, objects));
		objects.add((pageNum - 1) * pageSize);
		objects.add(pageSize);
		
		return jdbcTemplate.query(sql, objects.toArray(), new MapRowMapper());
	}
	
	public int countObjects(Equipment object)
	{
		String sql = "SELECT count(*) from college_equipment  %wheresql ";
		List<Object> objects = new ArrayList<Object>();
		sql = sql.replace("%wheresql", getWhereSql(object, objects));
		return jdbcTemplate.queryForObject(sql, objects.toArray(), Integer.class);
	}
	
	public Equipment getObjectById(int id)
	{
		String sql = "select * from college_equipment where id = ?";
		return jdbcTemplate.queryForObject(sql, new EquipmentRowMapper(), id);
	}
	
	public int addObject(Equipment object) 
	{
		String sql = "insert into college_equipment (name, brand, model, shelf, purchase, belong_type, owner, address) values (?, ?, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql, object.getName(), object.getBrand(), object.getModel(), object.getShelf(), object.getPurchase(), object.getBelong_type(), object.getOwner(), object.getAddress());
	}
	
	public int updateObject(Equipment object) 
	{
		String sql = "update college_equipment set name = ?, brand = ?, model = ?, shelf = ?, purchase = ?, belong_type= ?, owner= ?, address= ? where id = ?";
		return jdbcTemplate.update(sql, object.getName(), object.getBrand(), object.getModel(), object.getShelf(), object.getPurchase(), object.getBelong_type(), object.getOwner(), object.getAddress(), object.getId());
	}
	
	public int deleteObject(Equipment object) 
	{
		String sql = "delete from college_equipment where id = ?";
		return jdbcTemplate.update(sql, object.getId());
	}
	
	private String getWhereSql(Equipment equipment, List<Object> args)
	{
		StringBuffer sb = new StringBuffer();
		if (null != equipment.getId() && 0 != equipment.getId())
		{
			sb.append(" and id = ?");
			args.add(equipment.getId());
		}
		if (null != equipment.getBelong_type() && 0 != equipment.getBelong_type())
		{
			sb.append(" and belong_type = ?");
			args.add(equipment.getBelong_type());
		}
		if (null != equipment.getOwner() && 0 != equipment.getOwner())
		{
			sb.append(" and owner = ?");
			args.add(equipment.getOwner());
		}
		if (null != equipment.getAddress() && 0 != equipment.getAddress())
		{
			sb.append(" and address = ?");
			args.add(equipment.getAddress());
		}
		if (null != equipment.getName() && !"".equals(equipment.getName()))
		{
			sb.append(" and name like ?");
			args.add("%" + equipment.getName() + "%");
		}
		if (null != equipment.getBrand() && !"".equals(equipment.getBrand()))
		{
			sb.append(" and brand like ?");
			args.add("%" + equipment.getBrand() + "%");
		}
		if (null != equipment.getModel() && !"".equals(equipment.getModel()))
		{
			sb.append(" and model like ?");
			args.add("%" + equipment.getBrand() + "%");
		}
		if (null != equipment.getShelf() && !"".equals(equipment.getShelf()))
		{
			sb.append(" and shelf like ?");
			args.add("%" + equipment.getShelf() + "%");
		}
		if (null != equipment.getPurchase() && !"".equals(equipment.getPurchase()))
		{
			sb.append(" and purchase like ?");
			args.add("%" + equipment.getPurchase() + "%");
		}
		
		if (sb.length() != 0)
		{
			return "where " + sb.substring(4);
		}
		return sb.toString();
	}
}

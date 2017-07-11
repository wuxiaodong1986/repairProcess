package com.college.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.college.dao.rowMapper.AddressRowMapper;
import com.college.dao.rowMapper.AddressTreeRowMapper;
import com.college.dto.Address;
import com.common.utils.db.MapRowMapper;
import com.common.utils.tree.Tree;

@Repository
public class AddressDao 
{
	@Autowired
	@Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
	
	public List<Tree> getCollegeAddresss()
	{
		String sql = "SELECT a.*, (a.id+0) orderid FROM college_address a ORDER BY orderid";
		
		return jdbcTemplate.query(sql, new AddressTreeRowMapper());
	}
	
	public List<Map<String, Object>> getObjects(Address object, int pageNum, int pageSize)
	{
		String sql = "select a.*, (a.id+0) orderid from college_address a  %wheresql ORDER BY orderid limit ? , ?";
		sql = sql.replace("%wheresql", getWhereSql(object));
		
		return jdbcTemplate.query(sql, new MapRowMapper(), (pageNum - 1) * pageSize, pageSize);
	}
	
	public int countObjects(Address object) 
	{
		String sql = "select count(*) from college_address %wheresql ";
		sql = sql.replace("%wheresql", getWhereSql(object));
		
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public Address getObjectById(Address object)
	{
		String sql = "select * from college_address where id = ?";
		
		return jdbcTemplate.query(sql, new AddressRowMapper(), object.getId()).get(0);
	}
	
	public int addObject(Address object)
	{
		String sql = "insert into college_address (id, p_id, name, dscb) values (?, ?, ?, ?)";
		return jdbcTemplate.update(sql, object.getId(), object.getP_id(), object.getName(), object.getDscb());
	}
	
	public int updateObject(Address object)
	{
		String sql = "update college_address set p_id = ?, name = ?, dscb = ? where id = ?";
		return jdbcTemplate.update(sql, object.getP_id(), object.getName(), object.getDscb(), object.getId());
	}
	
	public int deleteObject(Address object)
	{
		String sql = "delete from college_address where id = ?";
		
		return jdbcTemplate.update(sql, object.getId());
	}
	
	private String getWhereSql(Address object)
	{
		StringBuffer sb = new StringBuffer();
		if (null != object.getId() && !"".equals(object.getId()))
		{
			sb.append(" and id = '");
			sb.append(object.getId());
			sb.append("'");
		}
		if (null != object.getP_id() && !"".equals(object.getP_id()))
		{
			sb.append(" and p_id = '");
			sb.append(object.getP_id());
			sb.append("'");
		}
		if (null != object.getName() && !"".equals(object.getName()))
		{
			sb.append(" and name like '%");
			sb.append(object.getName());
			sb.append("%'");
		}
		
		if (sb.length() != 0)
		{
			return "where " + sb.substring(4);
		}
		return sb.toString();
	}
}

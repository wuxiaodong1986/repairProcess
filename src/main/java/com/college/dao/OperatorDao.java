package com.college.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.college.dao.rowMapper.OperatorCacheRowMapper;
import com.college.dto.Operator;
import com.common.utils.db.MapRowMapper;
import com.common.utils.reflect.ReflectUtils;

@Repository("collegeOperatorDao")
public class OperatorDao 
{
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> getObjects(Operator object, int pageNum, int pageSize)
	{
		String sql = "SELECT * FROM base_operator  %wheresql order by id limit ? , ?";
		List<Object> objects = new ArrayList<Object>();
		sql = sql.replace("%wheresql", getWhereSql(object, objects));
		objects.add((pageNum - 1) * pageSize);
		objects.add(pageSize);
		
		return jdbcTemplate.query(sql, objects.toArray(), new MapRowMapper());
	}
	
	public int countObjects(Operator object)
	{
		String sql = "SELECT count(*) from base_operator  %wheresql ";
		List<Object> objects = new ArrayList<Object>();
		sql = sql.replace("%wheresql", getWhereSql(object, objects));
		return jdbcTemplate.queryForObject(sql, objects.toArray(), Integer.class);
	}
	
	public Operator getObjectById(int id) 
	{
		String sql = "select * from base_operator where id = ?";
		
		Operator operator = new Operator();
		ReflectUtils.mapToObject(jdbcTemplate.queryForObject(sql, new MapRowMapper(), id), operator);
		
		return operator; 
	}
	
	public int deleteObject(Operator object)
	{
		String sql = "delete from base_operator where id = ?";
		
		return jdbcTemplate.update(sql, object.getId());
	}
	
	public List<Map<String, Object>> getOperatorsByOrg(String staff_org_id)
	{
		String sql = "select * from base_operator where staff_org_id = ?";
		return jdbcTemplate.query(sql, new MapRowMapper(), staff_org_id);
	}
	
	public int addStaff(Operator object)
	{
		String sql = "insert into base_operator (username, name, staff_org_id, staff_position, staff_workaddress, staff_room, phone, status, opr_type) valuse (?, ?, ?, ?, ?, ?, ?, 2)";
		return jdbcTemplate.update(sql, object.getUsername(), object.getName(), object.getStaff_org_id(), object.getStaff_position(), object.getStaff_workaddress(), object.getStaff_room(), object.getPhone(), object.getStatus());
	}
	
	public int updateStaff(Operator object)
	{
		String sql = "update base_operator set username = ?, name = ?, staff_org_id = ?, staff_position = ?, staff_workaddress, staff_room, phone = ?, status = ? where id = ?";
		return jdbcTemplate.update(sql, object.getUsername(), object.getName(), object.getStaff_org_id(), object.getStaff_position(), object.getPhone(), object.getStatus(), object.getId());
	}
	
	public int updateStaffSelf(Operator object)
	{
		String sql = "update base_operator set name = ?, staff_workaddress = ?, staff_room = ?, phone = ? where id = ?";
		return jdbcTemplate.update(sql, object.getName(), object.getStaff_workaddress(), object.getStaff_room(), object.getPhone(), object.getId());
	}
	
	public List<Operator> getOperatorForCache()
	{
		String sql = "select id, username, name, staff_org_id from base_operator ";
		
		return jdbcTemplate.query(sql, new OperatorCacheRowMapper());
	}
	
	public boolean checkOperatorRole(int opr_id, int roleid)
	{
		String sql = "select count(*) from base_opr_role where opr_id = ? and roleid = ?";
		
		return jdbcTemplate.queryForObject(sql, Integer.class, opr_id, roleid) != 0;
	}
	
	private String getWhereSql(Operator object, List<Object> args)
	{
		StringBuffer sb = new StringBuffer();
		if (null != object.getId() && 0 != object.getId())
		{
			sb.append(" and id = ?");
			args.add(object.getId());
		}
		if (null != object.getStatus() && 0 != object.getStatus())
		{
			sb.append(" and status = ?");
			args.add(object.getStatus());
		}
		if (null != object.getOpr_type() && 0 != object.getOpr_type())
		{
			sb.append(" and opr_type = ?");
			args.add(object.getOpr_type());
		}
		if (null != object.getUsername() && !"".equals(object.getUsername()))
		{
			sb.append(" and username = ?");
			args.add(object.getUsername());
		}
		if (null != object.getName() && !"".equals(object.getName()))
		{
			sb.append(" and name like ?");
			args.add("%" + object.getName() + "%");
		}
		if (null != object.getStaff_org_id() && !"".equals(object.getStaff_org_id()))
		{
			sb.append(" and staff_org_id = ?");
			args.add(object.getStaff_org_id());
		}
		if (null != object.getStaff_position() && !"".equals(object.getStaff_position()))
		{
			sb.append(" and staff_position = ?");
			args.add(object.getStaff_position());
		}
		if (null != object.getStu_grade() && !"".equals(object.getStu_grade()))
		{
			sb.append(" and stu_grade = ?");
			args.add(object.getStu_grade());
		}
		if (null != object.getStu_major_id() && !"".equals(object.getStu_major_id()))
		{
			sb.append(" and stu_major_id = ?");
			args.add(object.getStu_major_id());
		}
		
		if (sb.length() != 0)
		{
			return "where " + sb.substring(4);
		}
		return sb.toString();
	}
}

package com.college.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.college.dao.rowMapper.OrgRowMapper;
import com.college.dao.rowMapper.OrgTreeRowMapper;
import com.college.dto.Org;
import com.common.utils.db.MapRowMapper;
import com.common.utils.tree.Tree;

@Repository
public class OrgDao 
{
	@Autowired
	@Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
	
	public List<Tree> getCollegeOrgs()
	{
		String sql = "SELECT a.*, (a.id+0) orderid FROM college_org a ORDER BY orderid";
		
		return jdbcTemplate.query(sql, new OrgTreeRowMapper());
	}
	
	public List<Map<String, Object>> getObjects(Org object, int pageNum, int pageSize)
	{
		String sql = "select a.*, (a.id+0) orderid from college_org a  %wheresql ORDER BY orderid limit ? , ?";
		sql = sql.replace("%wheresql", getWhereSql(object));
		
		return jdbcTemplate.query(sql, new MapRowMapper(), (pageNum - 1) * pageSize, pageSize);
	}
	
	public int countObjects(Org object) 
	{
		String sql = "select count(*) from college_org %wheresql ";
		sql = sql.replace("%wheresql", getWhereSql(object));
		
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public Org getObjectById(Org object)
	{
		String sql = "select * from college_org where id = ?";
		
		List<Org> orgs = jdbcTemplate.query(sql, new OrgRowMapper(), object.getId());
		if (orgs.size() > 0)
		{
			return orgs.get(0);
		}
		else
		{
			return null;
		}
	}
	
	public int addObject(Org object)
	{
		String sql = "insert into college_org (id, p_id, name) values (?, ?, ?)";
		return jdbcTemplate.update(sql, object.getId(), object.getP_id(), object.getName());
	}
	
	public int updateObject(Org object)
	{
		String sql = "update college_org set p_id = ?, name = ? where id = ?";
		return jdbcTemplate.update(sql, object.getP_id(), object.getName(), object.getId());
	}
	
	public int deleteObject(Org object)
	{
		String sql = "delete from college_org where id = ?";
		
		return jdbcTemplate.update(sql, object.getId());
	}
	
	private String getWhereSql(Org object)
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

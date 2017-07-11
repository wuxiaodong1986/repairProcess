package com.college.dao.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.college.dto.Operator;

public class OperatorCacheRowMapper implements RowMapper<Operator>
{
	@Override
	public Operator mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Operator operator = new Operator();
		
		operator.setId(rs.getInt("id"));
		operator.setUsername(rs.getString("username"));
		operator.setName(rs.getString("name"));
		operator.setStaff_org_id(rs.getString("staff_org_id"));
		
		return operator;
	}

}

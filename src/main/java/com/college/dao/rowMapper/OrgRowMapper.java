package com.college.dao.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.college.dto.Org;

public class OrgRowMapper implements RowMapper<Org>
{
	@Override
	public Org mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Org org = new Org();
		
		org.setId(rs.getString("id"));
		org.setP_id(rs.getString("p_id"));
		org.setName(rs.getString("name"));
		
		return org;
	}

}

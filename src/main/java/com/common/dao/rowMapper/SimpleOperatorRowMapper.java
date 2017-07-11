package com.common.dao.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.college.dto.Operator;


public class SimpleOperatorRowMapper implements RowMapper<Operator> 
	{
		@Override
		public Operator mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			Operator operator = new Operator();
			
			operator.setId(rs.getInt("id"));
			operator.setUsername(rs.getString("username"));
			operator.setStatus(rs.getInt("status"));
			operator.setName(rs.getString("name"));
			
			return operator;
		}
	}
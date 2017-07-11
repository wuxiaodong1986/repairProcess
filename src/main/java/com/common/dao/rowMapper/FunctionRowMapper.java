package com.common.dao.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.common.dto.Function;


public class FunctionRowMapper implements RowMapper<Function> 
	{
		@Override
		public Function mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			Function function = new Function();
			
			function.setId(rs.getString("id"));
			function.setP_id(rs.getString("p_id"));
			function.setIshow(rs.getInt("ishow"));
			function.setName(rs.getString("name"));
			function.setUrl(rs.getString("url"));
			function.setLogo(rs.getString("logo"));
			function.setLogname(rs.getString("logname"));
			
			return function;
		}
	}
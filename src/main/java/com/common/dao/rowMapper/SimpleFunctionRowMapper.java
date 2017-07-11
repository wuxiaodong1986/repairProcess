package com.common.dao.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.common.dto.Function;
import com.common.utils.tree.Tree;


public class SimpleFunctionRowMapper implements RowMapper<Tree> 
	{
		@Override
		public Tree mapRow(ResultSet rs, int rowNum) throws SQLException 
		{
			Function function = new Function();
			
			function.setId(rs.getString("id"));
			function.setP_id(rs.getString("p_id"));
			function.setIshow(rs.getInt("ishow"));
			function.setUrl(rs.getString("url"));
			
			return function;
		}
	}
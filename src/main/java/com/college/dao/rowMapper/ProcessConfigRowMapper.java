package com.college.dao.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.college.dto.ProcessConfig;

public class ProcessConfigRowMapper implements RowMapper<ProcessConfig>
{
	@Override
	public ProcessConfig mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		ProcessConfig processConfig = new ProcessConfig();
		
		processConfig.setId(rs.getInt("id"));
		processConfig.setCode(rs.getString("code"));
		processConfig.setRoleid(rs.getInt("roleid"));
		processConfig.setList(rs.getInt("list"));
		processConfig.setImplbean(rs.getString("implbean"));
		processConfig.setDscb(rs.getString("dscb"));
		
		return processConfig;
	}

}

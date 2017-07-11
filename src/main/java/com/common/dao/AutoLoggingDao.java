package com.common.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.common.dto.AutoLogging;



@Repository
public class AutoLoggingDao 
{
	@Autowired
	@Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
	
	public int logging(AutoLogging autoLogging)
	{
		String sql = "insert into base_autologging (logtime,oid,username,name,logname, url,mid,parameters) values(now(), ?, ?, ?, ?, ?, ?, ?) ";
		
		return jdbcTemplate.update(sql, autoLogging.getOid(), autoLogging.getUsername(), autoLogging.getName(), autoLogging.getLogname(), autoLogging.getUrl(), autoLogging.getMid(), autoLogging.getParameters());
	}
}

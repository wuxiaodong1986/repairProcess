package com.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.common.utils.db.MapRowMapper;


@Repository
public class ConfigDao 
{
	@Autowired
	@Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> getConfigs()
	{
		String sql = "select * from base_config";
		
		return jdbcTemplate.query(sql, new MapRowMapper());
	}
}

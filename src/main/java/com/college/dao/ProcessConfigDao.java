package com.college.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.college.dao.rowMapper.ProcessConfigRowMapper;
import com.college.dto.ProcessConfig;
import com.common.utils.db.ObjectRowMapper;

@Repository
public class ProcessConfigDao 
{
	@Autowired
	@Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
	
	public List<ProcessConfig> getCollegeProcesses()
	{
		String sql = "select * from college_process_config order by code, list";
		
		return jdbcTemplate.query(sql, new ProcessConfigRowMapper());
	}
	
	public List<ProcessConfig> getCollegeProcessesByOid(int oid)
	{
		String sql = "SELECT distinct b.implbean FROM base_opr_role a, college_process_config b where a.roleid = b.roleid and a.opr_id = ? ";
		return jdbcTemplate.query(sql, new ObjectRowMapper<ProcessConfig>(ProcessConfig.class), oid);
	}
}

package com.common.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.common.dao.rowMapper.FunctionRowMapper;
import com.common.dao.rowMapper.SimpleFunctionRowMapper;
import com.common.dto.Function;
import com.common.utils.tree.Tree;


@Repository
public class FunctionDao 
{
	@Autowired
	@Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
	
	public List<Tree> getFunctionsByOprId(int opr_id)
	{
		String sql = "SELECT distinct a.id, a.p_id, a.ishow, a.url FROM base_function a, base_rolefuns b, base_opr_role c where a.id = b.funid and b.roleid = c.roleid and c.opr_id= ? ORDER BY a.id";
		
		return jdbcTemplate.query(sql, new SimpleFunctionRowMapper(), opr_id);
	}
	
	public List<Function> getFunctions()
	{
		String sql = "SELECT * FROM base_function ORDER BY id";
		
		return jdbcTemplate.query(sql, new FunctionRowMapper());
	}
	
}

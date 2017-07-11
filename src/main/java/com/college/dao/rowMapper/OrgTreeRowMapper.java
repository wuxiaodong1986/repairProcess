package com.college.dao.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.college.dto.Org;
import com.common.utils.tree.Tree;

public class OrgTreeRowMapper implements RowMapper<Tree>
{
	@Override
	public Tree mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Org org = new Org();
		
		org.setId(rs.getString("id"));
		org.setP_id(rs.getString("p_id"));
		org.setName(rs.getString("name"));
		
		return org;
	}

}

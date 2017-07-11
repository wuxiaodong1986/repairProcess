package com.college.dao.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.college.dto.Address;
import com.common.utils.tree.Tree;

public class AddressTreeRowMapper implements RowMapper<Tree>
{
	@Override
	public Tree mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Address address = new Address();
		
		address.setId(rs.getInt("id"));
		address.setP_id(rs.getInt("p_id"));
		address.setName(rs.getString("name"));
		address.setDscb(rs.getString("dscb"));
		
		return address;
	}

}

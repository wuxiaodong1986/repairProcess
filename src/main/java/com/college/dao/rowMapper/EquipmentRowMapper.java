package com.college.dao.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.college.dto.Equipment;

public class EquipmentRowMapper implements RowMapper<Equipment>
{
	@Override
	public Equipment mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Equipment equipment = new Equipment();
		equipment.setId(rs.getInt("id"));
		equipment.setBelong_type(rs.getInt("belong_type"));
		equipment.setOwner(rs.getInt("owner"));
		equipment.setAddress(rs.getInt("address"));
		equipment.setName(rs.getString("name"));
		equipment.setBrand(rs.getString("brand"));
		equipment.setModel(rs.getString("model"));
		equipment.setShelf(rs.getString("shelf"));
		equipment.setPurchase(rs.getString("purchase"));
		return equipment;
	}

}

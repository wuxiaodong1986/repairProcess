package com.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.common.dao.rowMapper.SimpleOperatorRowMapper;
import com.college.dto.Operator;


@Repository
public class OperatorDao 
{
	@Autowired
	@Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
	
	public Operator getSimpleOperator(String username, String password)
	{
		String sql = "select * from base_operator where username = ? and password = ?";
		
		List<Operator> operators = jdbcTemplate.query(sql, new SimpleOperatorRowMapper(), username, password);
		if (operators.size() == 0)
		{
			return null;
		}
		else
		{
			return operators.get(0);
		}
	}
	
	public int addOperatorReturnId(final Operator object)
	{
		final String sql = "insert into base_operator (username, password, status) values (?, ?, ?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() 
		{
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException 
			{
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, object.getUsername());
				ps.setString(2, object.getPassword());
				ps.setInt(3, object.getStatus());
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public int updatePassword(Integer id, String oldPassword, String newPassword) 
	{
		String sql = "update base_operator set password = ? where id = ? and password = ?";
		return jdbcTemplate.update(sql, newPassword, id, oldPassword);
	}
}

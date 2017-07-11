package com.common.utils.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("hiding")
public class ObjectRowMapper<Object> implements RowMapper<Object>
{
	@SuppressWarnings("rawtypes")
	private Class objectClass;
	
	@SuppressWarnings("rawtypes")
	public ObjectRowMapper(Class objectClass)
	{
		this.objectClass = objectClass;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Object object = null;
		
		try 
		{
			object = (Object)objectClass.newInstance();
			
			DBUtils.initObjectByRs(rs, object);
		} 
		catch (InstantiationException e) 
		{
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) 
		{
			e.printStackTrace();
		}
		
		return object;
	}
	
}

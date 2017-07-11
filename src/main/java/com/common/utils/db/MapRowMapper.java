package com.common.utils.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;



import org.springframework.jdbc.core.RowMapper;

public class MapRowMapper implements RowMapper<Map<String, Object>> 
{

	@Override
	public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		ResultSetMetaData rsmd = rs.getMetaData();
		
		for (int i = 1; i <= rsmd.getColumnCount(); i++)
		{
			String name = rsmd.getColumnName(i);
			int type = rsmd.getColumnType(i);
			
			if (type == Types.VARCHAR || type == Types.CHAR || type == Types.DATE || type == Types.TIME)
			{
				String value = rs.getString(name);
				map.put(name, value);
			}
			else if (type == Types.INTEGER || type == Types.SMALLINT)
			{
				Integer value = rs.getInt(name);
				map.put(name, value);
			}
			else if (type == Types.DOUBLE)
			{
				Double value = rs.getDouble(name);
				map.put(name, value);
			}
			else if (type == Types.FLOAT)
			{
				Float value = rs.getFloat(name);
				map.put(name, value);
			}
			else 
			{
				String value = rs.getString(name);
				map.put(name, value);
			}
		}
		
		return map;
	}
	
}

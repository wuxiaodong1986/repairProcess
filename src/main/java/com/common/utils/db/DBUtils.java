package com.common.utils.db;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils 
{
	 public static void initObjectByRs(ResultSet rs, Object object) throws SQLException
	 {
		 ResultSetMetaData rsmd = rs.getMetaData();
		 
		 Method[] methods = object.getClass().getDeclaredMethods();
		 Map<String, Object> nameValueMap = new HashMap<String, Object>();
		 
		 for (int i = 1; i <= rsmd.getColumnCount(); i++)
		 {
			 String columnName = rsmd.getColumnName(i);
			 try 
			 {
				for (Method method : methods)
				 {
					 if (method.getName().startsWith("set"))
					 {
						 String methodName = method.getName().substring(3);
						 
						 if (methodName.equalsIgnoreCase(columnName))
						 {
							 if (null == nameValueMap.get(methodName))
							 {
								nameValueMap.put(methodName, methodName);
							 }
							 else
							 {
								continue;
							 }
							 
							 if (int.class == method.getParameterTypes()[0] || Integer.class == method.getParameterTypes()[0])
							 {
								 int columnValue = rs.getInt(i);
								 method.invoke(object, columnValue);
							 }
							 else if (double.class == method.getParameterTypes()[0] || Double.class == method.getParameterTypes()[0])
							 {
								 double columnValue = rs.getDouble(i);
								 method.invoke(object, columnValue);
							 }
							 else if (String.class == method.getParameterTypes()[0])
							 {
								 String columnValue = rs.getString(i);
								 method.invoke(object, columnValue);
							 }
							 else
							 {
								 Object columnValue = rs.getObject(i);
								 method.invoke(object, columnValue);
							 }
						 }
					 }
				 }
			} 
			 catch (IllegalAccessException e) 
			{
				e.printStackTrace();
			} 
			 catch (IllegalArgumentException e) 
			{
				e.printStackTrace();
			}
			 catch (InvocationTargetException e) 
			{
				e.printStackTrace();
			}
		 }
	 }
	 
	 
//	 public static String getWhereSql(CommonDto object)
//		{
//		 	Map<String, ArgType> argtypes = object.argtypes();
//		 	
//			StringBuffer sb = new StringBuffer();
//			
//			try 
//			{
//				Method[] methods = object.getClass().getDeclaredMethods();
//				Map<String, Object> nameValueMap = new HashMap<String, Object>();
//				
//				for (Method method : methods)
//				{
//					if (method.getName().startsWith("get") && !"getClass".equals(method.getName()))
//					{
//						String name = method.getName().replace("get", "");
//						Object value = method.invoke(object);
//						
//						if (null == nameValueMap.get(name))
//						{
//							nameValueMap.put(name, value);
//						}
//						else
//						{
//							continue;
//						}
//						
//						if (String.class.equals(method.getReturnType()))
//						{
//							
//							if (null != value && !"".equals(value))
//							{
//								if (argtypes != null && argtypes.get(name) != null && "N".equals(argtypes.get(name).getIflike()))
//								{
//									sb.append(" and " + name + " = '");
//									sb.append(value.toString());
//									sb.append("'");
//								}
//								else
//								{
//									sb.append(" and " + name + " like '%");
//									sb.append(value.toString());
//									sb.append("%'");
//								}
//							}
//						}
//						else if (int.class.equals(method.getReturnType()) || Integer.class.equals(method.getReturnType()))
//						{
//							if (null != value &&  0 != (Integer)value)
//							{
//								sb.append(" and " + name + " = ");
//								sb.append(value.toString());
//							}
//						}
//						else if (double.class.equals(method.getReturnType()) || Double.class.equals(method.getReturnType()))
//						{
//							if (null != value && 0 != (Double)value)
//							{
//								sb.append(" and " + name + " = ");
//								sb.append(value.toString());
//							}
//						}
//					}
//				}
//				
//				if (sb.length() != 0)
//				{
//					return "where " + sb.substring(4);
//				}
//			} 
//			catch (Exception e) 
//			{
//				e.printStackTrace();
//			}
//			
//			return sb.toString();
//		}
}

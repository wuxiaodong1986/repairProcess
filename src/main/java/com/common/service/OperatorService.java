package com.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.OperatorDao;
import com.college.dto.Operator;


@Service
public class OperatorService 
{
	@Autowired
	private OperatorDao operatorDao;
	
	public Operator getSimpleOperator(String username, String password)
	{
		return operatorDao.getSimpleOperator(username, password);
	}

	public int updatePassword(Integer id, String oldPassword, String newPassword) 
	{
		return operatorDao.updatePassword(id, oldPassword, newPassword);
	}
}

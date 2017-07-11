package com.college.service.repairProcess.intf;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.college.dto.Operator;
import com.college.dto.RepairInfo;

public interface RepairProcess 
{
	public String page();
	
	public String list(Operator operator);
	
	public void next(RepairInfo collegeRepairInfo);
	
	public void back(ModelAndView modelAndView, HttpServletRequest request);
	
	public void cancel(ModelAndView modelAndView, HttpServletRequest request);
	
	public void finish(ModelAndView modelAndView, HttpServletRequest request);
}

package com.college.service.repairProcess;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.college.dto.RepairInfo;
import com.college.service.RepairService;
import com.college.service.repairProcess.intf.RepairProcess;
import com.college.dto.Operator;


@Component("staffRepairProcess")
public class StaffRepairProcess implements RepairProcess 
{
	@Autowired
	private RepairService repairService;
	
	@Override
	public String list(Operator operator) 
	{
		return "a.o_id = " + operator.getId();
	}
	
	@Override
	public String page() 
	{
		return "college/process/viewRepairInfo";
	}

	@Override
	public void next(RepairInfo repairInfo) 
	{
		repairService.update(repairInfo);
	}

	@Override
	public void back(ModelAndView modelAndView, HttpServletRequest request) 
	{
		
	}

	@Override
	public void cancel(ModelAndView modelAndView, HttpServletRequest request) 
	{
		
	}
	
	@Override
	public void finish(ModelAndView modelAndView, HttpServletRequest request) 
	{
		
	}

}

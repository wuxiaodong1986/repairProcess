package com.college.service.repairProcess;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.college.dto.RepairInfo;
import com.college.service.RepairService;
import com.college.service.repairProcess.intf.RepairProcess;
import com.college.dto.Operator;

@Component("applyRepairProcess")
public class ApplyRepairProcess implements RepairProcess 
{
	@Autowired
	private RepairService repairService;
	
	@Override
	public String page() 
	{
		return "";
	}

	@Override
	public String list(Operator operator) 
	{
		return "1 = 0";
	}

	@Override
	public void next(RepairInfo repairInfo) 
	{
//		if (repairInfo.getBelong_type() == 2)
//		{
//			repairInfo.setStatus(1);
//		}
//		if (repairInfo.getBelong_type() == 3)
//		{
//			repairInfo.setStatus(9);
//		}
		repairService.add(repairInfo);
	}

	@Override
	public void back(ModelAndView modelAndView, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancel(ModelAndView modelAndView, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finish(ModelAndView modelAndView, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

}

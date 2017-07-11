package com.college.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.college.dto.Address;
import com.college.dto.Equipment;
import com.college.dto.Operator;
import com.college.dto.ProcessConfig;
import com.college.dto.RepairInfo;
import com.college.service.AddressService;
import com.college.service.EquipmentService;
import com.college.service.OperatorService;
import com.college.service.RepairService;
import com.college.service.repairProcess.CollegeProcessConfigCenter;
import com.common.utils.cache.CacheUtils;


@Controller
public class RepairController 
{
	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private OperatorService operatorService;
	
	@Autowired
	private RepairService repairService;
	
	@Autowired
	@Qualifier("configMapMap")
	private Object configMapMap;
	
	@Autowired
	@Qualifier("processConfigNameMap")
	private Object processConfigNameMap;
	
	@Autowired
	@Qualifier("collegeProcessConfigs")
	private Object collegeProcessConfigs;
	
	@Autowired
	@Qualifier("collegeProcessConfigCenter")
	private CollegeProcessConfigCenter collegeProcessConfigCenter;
	
	@RequestMapping(value = "/staffRepairEquipSearch.htm")
    public String staffRepairEquipSearch()
    {
        return "college/staffRepairEquipSearch";
    }
	
	@RequestMapping(value = "/pubRepairEquipSearch.htm")
    public String pubRepairEquipSearch()
    {
        return "college/pubRepairEquipSearch";
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/repairEquips.htm")
    public MappingJacksonJsonView repairEquips(HttpSession session, Equipment object, @RequestParam(value="pageNum", required = false, defaultValue = "1")int pageNum)
    {
		MappingJacksonJsonView jsonView = new MappingJacksonJsonView();
		
		List<Map<String, Object>> objects = equipmentService.getObjects(object, pageNum);
		int count = equipmentService.countObjects(object);
		
		Object collegeAddressMap = session.getServletContext().getAttribute("collegeAddressMap");
		addressService.changeAddressIdToName(objects, (Map<Integer, Address>)collegeAddressMap, "address");
		
		jsonView.addStaticAttribute("objects", objects);
		jsonView.addStaticAttribute("count", count);
		jsonView.addStaticAttribute("pageNum", pageNum);
		
        return jsonView;
    }
	
	@RequestMapping(value = "/preApplyRepair.htm")
	public ModelAndView preApplyRepair(HttpSession session, Equipment equipment)
	{
		ModelAndView modelAndView = new ModelAndView();
		
		equipment = equipmentService.getObjectById(equipment.getId());
		
		RepairInfo object = new RepairInfo();
		
		if (equipment.getBelong_type() == 2)
		{
			object.setStatus(19);
		}
		if (equipment.getBelong_type() == 3)
		{
			object.setStatus(20);
		}
		modelAndView.addObject("equipment", equipment);
		modelAndView.addObject("object", object);
		
		String beanName = "applyRepairProcess";
		modelAndView.addObject("beanName", beanName);
		
		modelAndView.setViewName("college/processPage");
		return modelAndView;
	}
	
//	@RequestMapping(value = "/applyRepair.htm")
//	public ModelAndView applyRepair(RepairInfo object)
//	{
//		ModelAndView modelAndView = new ModelAndView();
//		
//		repairService.add(object);
//		
//		modelAndView.setViewName("college/repairInfoSearch");
//		
//		return modelAndView;
//	}
	
	@RequestMapping(value = "/repairInfoSearch.htm")
    public ModelAndView repairInfoSearch()
    {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("collegeProcessConfigs", collegeProcessConfigs);
		
		modelAndView.setViewName("college/repairInfoSearch");
		
		return modelAndView;
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/repairInfos.htm")
    public MappingJacksonJsonView repairInfos(HttpSession session, RepairInfo object, @RequestParam(value="pageNum", required = false, defaultValue = "1")int pageNum)
    {
		MappingJacksonJsonView jsonView = new MappingJacksonJsonView();
		
		Operator operator = (Operator)session.getAttribute("operator");
		
		List<Map<String, Object>> objects = repairService.getRepairInfos(operator, object, pageNum);
		int count = repairService.countRepairInfos(operator, object);
		
		Object collegeAddressMap = session.getServletContext().getAttribute("collegeAddressMap");
		addressService.changeAddressIdToName(objects, (Map<Integer, Address>)collegeAddressMap, "address");
		
		Object operatorMap = session.getServletContext().getAttribute("operatorMap");
		operatorService.changeOprIdToName(objects, (Map<Integer, Operator>)operatorMap, "owner");
		operatorService.changeOprIdToName(objects, (Map<Integer, Operator>)operatorMap, "o_id");
		
		CacheUtils.changeConfigValue(configMapMap, objects, "equm_type", "belong_type");
		CacheUtils.changeProcessConfigValue(collegeProcessConfigCenter, processConfigNameMap, objects, "status");
		
		jsonView.addStaticAttribute("objects", objects);
		jsonView.addStaticAttribute("count", count);
		jsonView.addStaticAttribute("pageNum", pageNum);
		
        return jsonView;
    }
	
	@RequestMapping(value = "/repairInfoPage.htm")
	public ModelAndView repairInfoPage(HttpSession session, RepairInfo object)
	{
		ModelAndView modelAndView = new ModelAndView();
		
		Operator operator = (Operator)session.getAttribute("operator");
		
		object = repairService.getRepairInfoById(object);
		
		Equipment equipment = equipmentService.getObjectById(object.getE_id());
		
		modelAndView.addObject("equipment", equipment);
		modelAndView.addObject("object", object);
		
		String beanName = repairService.getBeanName(operator, object);
		modelAndView.addObject("beanName", beanName);
		
		modelAndView.setViewName("college/processPage");
		return modelAndView;
	}
	
	@RequestMapping(value = "/nextRepairprocess.htm")
	public ModelAndView nextRepairprocess(RepairInfo object)
	{
		ModelAndView modelAndView = new ModelAndView();
		
		repairService.next(object);
		
		modelAndView.setViewName("college/repairInfoSearch");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/testProcess.htm")
	public ModelAndView testProcess(int id)
	{
		ModelAndView modelAndView = new ModelAndView();
		
		ProcessConfig config = collegeProcessConfigCenter.getNextStaffProcessConfig(id);
		
		System.out.println("id = "+ id +" , next id = " + config.getId());
		
		modelAndView.setViewName("college/repairInfoSearch");
		
		return modelAndView;
	}
}

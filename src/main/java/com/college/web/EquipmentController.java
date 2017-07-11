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
import com.college.service.AddressService;
import com.college.service.EquipmentService;
import com.college.service.OperatorService;
import com.common.utils.cache.CacheUtils;

@Controller
public class EquipmentController 
{
	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private OperatorService operatorService;
	
	@Autowired
	@Qualifier("configMapMap")
	private Object configMapMap;
	
	@RequestMapping(value = "/equipmentSearch.htm")
    public String equipmentSearch()
    {
        return "college/equipmentSearch";
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/equipmentList.htm")
    public MappingJacksonJsonView equipmentList(HttpSession session, Equipment object, @RequestParam(value="pageNum", required = false, defaultValue = "1")int pageNum)
    {
		MappingJacksonJsonView jsonView = new MappingJacksonJsonView();
		
		List<Map<String, Object>> objects = equipmentService.getObjects(object, pageNum);
		
		Object collegeAddressMap = session.getServletContext().getAttribute("collegeAddressMap");
		addressService.changeAddressIdToName(objects, (Map<Integer, Address>)collegeAddressMap, "address");
		
		Object operatorMap = session.getServletContext().getAttribute("operatorMap");
		operatorService.changeOprIdToName(objects, (Map<Integer, Operator>)operatorMap, "owner");
		
		CacheUtils.changeConfigValue(configMapMap, objects, "equm_type", "belong_type");
		
		int count = equipmentService.countObjects(object);
		
		jsonView.addStaticAttribute("objects", objects);
		jsonView.addStaticAttribute("count", count);
		jsonView.addStaticAttribute("pageNum", pageNum);
		
		return jsonView;
    }
	
	@RequestMapping(value = "/preUpdateEquipment.htm")
    public ModelAndView preUpdate(Equipment object, HttpSession session)
    {
		ModelAndView modelAndView = new ModelAndView();
		
		if (null != object.getId() && object.getId() != 0)
		{
			object = equipmentService.getObjectById(object.getId());
			Object operatorMap = session.getServletContext().getAttribute("operatorMap");
			@SuppressWarnings("unchecked")
			Operator operator = ((Map<Integer, Operator>)operatorMap).get(object.getOwner());
			if (null != operator)
			{
				object.setOwnerorg(operator.getStaff_org_id());
			}
		}
		modelAndView.addObject("object", object);
		modelAndView.setViewName("college/equipmentUpdate");
		return modelAndView;
    }
	
	@RequestMapping(value = "/updateEquipment.htm")
    public ModelAndView updateEquipment(HttpSession session, Equipment object,  @RequestParam(value="flag", required = false, defaultValue = "0")int flag)
    {
		ModelAndView modelAndView = new ModelAndView();
		
		if (object.getBelong_type() == 1)//无归属
		{
			object.setAddress(null);
			object.setOwner(null);
		}
		else if (object.getBelong_type() == 2)//教工
		{
			object.setAddress(null);
		}
		else if (object.getBelong_type() == 3)//公共
		{
			object.setOwner(null);
		}
		if (null == object.getId() ||  object.getId() == 0)
		{
			equipmentService.addObject(object);
		}
		else
		{
			equipmentService.updateObject(object);
		}
		
		modelAndView.setViewName("redirect:equipmentSearch.htm");
		return modelAndView;
    }
	
	@RequestMapping(value = "/delEquipment.htm")
    public ModelAndView delEquipment(HttpSession session, Equipment object)
    {
		ModelAndView modelAndView = new ModelAndView();
		
		equipmentService.deleteObject(object);
		
        modelAndView.setViewName("redirect:equipmentSearch.htm");
		return modelAndView;
    }
	
	
	@RequestMapping(value = "/myEquipmentSearch.htm")
    public String myEquipmentSearch()
    {
        return "college/myEquipmentSearch";
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/myEquipmentList.htm")
    public MappingJacksonJsonView myEquipmentList(HttpSession session, Equipment object, @RequestParam(value="pageNum", required = false, defaultValue = "1")int pageNum)
    {
		MappingJacksonJsonView jsonView = new MappingJacksonJsonView();
		
		Operator operator = (Operator)session.getAttribute("operator");
		
		object.setOwner(operator.getId());
		
		List<Map<String, Object>> objects = equipmentService.getObjects(object, pageNum);
		
		Object collegeAddressMap = session.getServletContext().getAttribute("collegeAddressMap");
		addressService.changeAddressIdToName(objects, (Map<Integer, Address>)collegeAddressMap, "address");
		
		Object operatorMap = session.getServletContext().getAttribute("operatorMap");
		operatorService.changeOprIdToName(objects, (Map<Integer, Operator>)operatorMap, "owner");
		
		CacheUtils.changeConfigValue(configMapMap, objects, "equm_type", "belong_type");
		
		int count = equipmentService.countObjects(object);
		
		jsonView.addStaticAttribute("objects", objects);
		jsonView.addStaticAttribute("count", count);
		jsonView.addStaticAttribute("pageNum", pageNum);
		
		return jsonView;
    }
	
	@RequestMapping(value = "/preUpdateMyEquipment.htm")
    public ModelAndView preUpdateMyEquipment(Equipment object, HttpSession session)
    {
		ModelAndView modelAndView = new ModelAndView();
		
		if (null != object.getId() && object.getId() != 0)
		{
			object = equipmentService.getObjectById(object.getId());
			Object operatorMap = session.getServletContext().getAttribute("operatorMap");
			@SuppressWarnings("unchecked")
			Operator operator = ((Map<Integer, Operator>)operatorMap).get(object.getOwner());
			if (null != operator)
			{
				object.setOwnerorg(operator.getStaff_org_id());
			}
		}
		modelAndView.addObject("object", object);
		modelAndView.setViewName("college/myEquipmentUpdate");
		return modelAndView;
    }
	
	@RequestMapping(value = "/updateMyEquipment.htm")
    public ModelAndView updateMyEquipment(HttpSession session, Equipment object,  @RequestParam(value="flag", required = false, defaultValue = "0")int flag)
    {
		ModelAndView modelAndView = new ModelAndView();
		
		Operator operator = (Operator)session.getAttribute("operator");
		
		object.setOwner(operator.getId());
		
		object.setBelong_type(2);
		object.setAddress(null);
		
		
		if (null == object.getId() ||  object.getId() == 0)
		{
			equipmentService.addObject(object);
		}
		else
		{
			equipmentService.updateObject(object);
		}
		
		modelAndView.setViewName("redirect:myEquipmentSearch.htm");
		return modelAndView;
    }
	
	@RequestMapping(value = "/delMyEquipment.htm")
    public ModelAndView delMyEquipment(HttpSession session, Equipment object)
    {
		ModelAndView modelAndView = new ModelAndView();
		
		equipmentService.deleteObject(object);
		
        modelAndView.setViewName("redirect:myEquipmentSearch.htm");
		return modelAndView;
    }
}

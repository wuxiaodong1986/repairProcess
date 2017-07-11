package com.college.web;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.college.dto.Operator;
import com.college.dto.Org;
import com.college.service.OperatorService;
import com.college.service.OrgService;

@Controller
public class OperatorController 
{
	@Autowired
	private OperatorService operatorService;
	
	@Autowired
	private OrgService orgService;
	
	@RequestMapping(value = "/staffSearch.htm")
    public String staffSearch()
    {
        return "college/staffSearch";
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/collegeOperatorList.htm")
    public MappingJacksonJsonView collegeOperatorList(HttpSession session, Operator object, @RequestParam(value="pageNum", required = false, defaultValue = "1")int pageNum)
    {
		MappingJacksonJsonView jsonView = new MappingJacksonJsonView();
		
		List<Map<String, Object>> objects = operatorService.getObjects(object, pageNum);
		Object collegeOrgMap = session.getServletContext().getAttribute("collegeOrgMap");
		orgService.changeOrgIdToName(objects, (Map<String, Org>)collegeOrgMap, "staff_org_id");
		
		int count = operatorService.countObjects(object);
		
		jsonView.addStaticAttribute("objects", objects);
		jsonView.addStaticAttribute("count", count);
		jsonView.addStaticAttribute("pageNum", pageNum);
		
        return jsonView;
    }
	
	@RequestMapping(value = "/preUpdateStaff.htm")
    public ModelAndView preUpdateStaff(Operator object)
    {
		ModelAndView modelAndView = new ModelAndView();
		
		if (null != object.getId() && object.getId() != 0)
		{
			object = operatorService.getObjectById(object.getId());
		}
		modelAndView.addObject("object", object);
		modelAndView.setViewName("college/staffUpdate");
		return modelAndView;
    }
	
	@RequestMapping(value = "/updateStaff.htm")
    public ModelAndView updateStaff(HttpSession session, Operator object)
    {
		ModelAndView modelAndView = new ModelAndView();
		
		if (null == object.getId() ||  object.getId() == 0)
		{
			object.setOpr_type(2);
			operatorService.addStaff(object);
		}
		else
		{
			operatorService.updateStaff(object);
		}
		
		reflushCash(session);
		
		modelAndView.setViewName("redirect:staffSearch.htm");
		return modelAndView;
    }
	
	@RequestMapping(value = "/delCollegeOperator.htm")
    public ModelAndView delCollegeOperator(HttpSession session, Operator object)
    {
		ModelAndView modelAndView = new ModelAndView();
		operatorService.deleteObject(object);
		
		reflushCash(session);
		
        modelAndView.setViewName("redirect:staffSearch.htm");
		return modelAndView;
    }
	
	@RequestMapping(value = "/getJsonOrgOperators.htm")
    public MappingJacksonJsonView getJsonOrgOperators(@RequestParam(value="staff_org_id", required = false)String staff_org_id)
    {
		MappingJacksonJsonView jsonView = new MappingJacksonJsonView();
		
		List<Map<String, Object>> operators = operatorService.getOperatorsByOrg(staff_org_id);
		
		jsonView.addStaticAttribute("operators", operators);
		
		return jsonView;
    }
	
	/**
	 * 重置servletContext中的机构缓存
	 * @param session
	 */
	private void reflushCash(HttpSession session)
	{
		ServletContext servletContext = session.getServletContext();
		WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		Object operatorMap = applicationContext.getBean("operatorMap");
		servletContext.setAttribute("operatorMap", operatorMap);
	}
}

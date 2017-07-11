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

import com.college.dto.Org;
import com.college.service.OrgService;

@Controller
public class OrgController 
{
	@Autowired
	private OrgService orgService;
	
	@RequestMapping(value = "/orgSearch.htm")
    public String orgSearch()
    {
        return "college/orgSearch";
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/orgList.htm")
    public MappingJacksonJsonView orgList(HttpSession session, Org org, @RequestParam(value="pageNum", required = false, defaultValue = "1")int pageNum)
    {
		MappingJacksonJsonView jsonView = new MappingJacksonJsonView();
		
		List<Map<String, Object>> objects = orgService.getObjects(org, pageNum);
		
		Object collegeOrgMap = session.getServletContext().getAttribute("collegeOrgMap");
		orgService.changeOrgIdToName(objects, (Map<String, Org>)collegeOrgMap, "p_id");
		
		int count = orgService.countObjects(org);
		
		jsonView.addStaticAttribute("objects", objects);
		jsonView.addStaticAttribute("count", count);
		jsonView.addStaticAttribute("pageNum", pageNum);
		
		return jsonView;
    }
	
	@RequestMapping(value = "/preUpdateOrg.htm")
    public ModelAndView preUpdateOrg(Org object)
    {
		ModelAndView modelAndView = new ModelAndView();
		
		if (null != object.getId() && !"".equals(object.getId().trim()))
		{
			object = orgService.getObjectById(object);
		}
		modelAndView.addObject("object", object);
		modelAndView.setViewName("college/orgUpdate");
		return modelAndView;
    }
	
	@RequestMapping(value = "/updateOrg.htm")
    public ModelAndView updateOrg(HttpSession session, Org object,  @RequestParam(value="flag", required = false, defaultValue = "0")int flag)
    {
		ModelAndView modelAndView = new ModelAndView();
		
		if (flag == 0)
		{
			Org checkorg = orgService.getObjectById(object);
			if (null != checkorg)
			{
				modelAndView.setViewName("frame/inError");
	            modelAndView.addObject("errorMessage", "机构号已存在");
	            modelAndView.addObject("errorCode", "500");
	            
	            return modelAndView;
			}
			orgService.addObject(object);
		}
		else
		{
			orgService.updateObject(object);
		}
		
		reflushCash(session);
		
		modelAndView.setViewName("redirect:orgSearch.htm");
		return modelAndView;
    }
	
	@RequestMapping(value = "/delOrg.htm")
    public ModelAndView delOrg(HttpSession session, Org org)
    {
		ModelAndView modelAndView = new ModelAndView();
		orgService.deleteObject(org);
		
		reflushCash(session);
		
        modelAndView.setViewName("redirect:orgSearch.htm");
		return modelAndView;
    }

	@RequestMapping(value = "/getJsonOrgTree.htm")
    public MappingJacksonJsonView orgTree(HttpSession session)
    {
		MappingJacksonJsonView jsonView = new MappingJacksonJsonView();
		
		Object collegeOrgTree = session.getServletContext().getAttribute("collegeOrgTree");
		
		jsonView.addStaticAttribute("tree", collegeOrgTree);
		
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
		Object collegeOrgTree = applicationContext.getBean("collegeOrgTree");
		servletContext.setAttribute("collegeOrgTree", collegeOrgTree);
		Object collegeOrgMap = applicationContext.getBean("collegeOrgMap");
		servletContext.setAttribute("collegeOrgMap", collegeOrgMap);
	}
}

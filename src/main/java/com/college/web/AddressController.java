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

import com.college.dto.Address;
import com.college.service.AddressService;

@Controller
public class AddressController 
{
	@Autowired
	private AddressService service;
	
	@RequestMapping(value = "/addressSearch.htm")
    public String addressSearch()
    {
        return "college/addressSearch";
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addressList.htm")
    public MappingJacksonJsonView addressList(HttpSession session, Address object, @RequestParam(value="pageNum", required = false, defaultValue = "1")int pageNum)
    {
		MappingJacksonJsonView jsonView = new MappingJacksonJsonView();
		
		List<Map<String, Object>> objects = service.getObjects(object, pageNum);
		
		Object collegeAddressMap = session.getServletContext().getAttribute("collegeAddressMap");
		service.changeAddressIdToName(objects, (Map<Integer, Address>)collegeAddressMap, "p_id");
		
		int count = service.countObjects(object);
		
		jsonView.addStaticAttribute("objects", objects);
		jsonView.addStaticAttribute("count", count);
		jsonView.addStaticAttribute("pageNum", pageNum);
		
		return jsonView;
    }
	
	@RequestMapping(value = "/preUpdateAddress.htm")
    public ModelAndView preUpdateAddress(Address object)
    {
		ModelAndView modelAndView = new ModelAndView();
		
		if (null != object.getId() && 0 != object.getId())
		{
			object = service.getObjectById(object);
		}
		modelAndView.addObject("object", object);
		modelAndView.setViewName("college/addressUpdate");
		return modelAndView;
    }
	
	@RequestMapping(value = "/updateAddress.htm")
    public ModelAndView updateAddress(HttpSession session, Address object,  @RequestParam(value="flag", required = false, defaultValue = "0")int flag)
    {
		ModelAndView modelAndView = new ModelAndView();
		
		if (null == object.getId() ||  "".equals(object.getId()))
		{
			service.addObject(object);
		}
		else
		{
			service.updateObject(object);
		}
		
		reflushCash(session);
		
		modelAndView.setViewName("redirect:addressSearch.htm");
		return modelAndView;
    }
	
	@RequestMapping(value = "/delAddress.htm")
    public ModelAndView delAddress(HttpSession session, Address object)
    {
		ModelAndView modelAndView = new ModelAndView();
		service.deleteObject(object);
		
		reflushCash(session);
		
        modelAndView.setViewName("redirect:addressSearch.htm");
		return modelAndView;
    }

	@RequestMapping(value = "/getJsonAddressTree.htm")
    public MappingJacksonJsonView addressTree(HttpSession session)
    {
		MappingJacksonJsonView jsonView = new MappingJacksonJsonView();
		
		Object collegeAddressTree = session.getServletContext().getAttribute("collegeAddressTree");
		
		jsonView.addStaticAttribute("tree", collegeAddressTree);
		
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
		Object collegeAddressTree = applicationContext.getBean("collegeAddressTree");
		servletContext.setAttribute("collegeAddressTree", collegeAddressTree);
		Object collegeAddressMap = applicationContext.getBean("collegeAddressMap");
		servletContext.setAttribute("collegeAddressMap", collegeAddressMap);
	}
}

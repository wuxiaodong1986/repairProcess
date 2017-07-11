package com.college.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class CacheListener implements ServletContextListener 
{
	@Override
	public void contextInitialized(ServletContextEvent sce) 
	{
		ServletContext servletContext = sce.getServletContext();
		WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
		
		//机构信息
		Object collegeOrgTree = applicationContext.getBean("collegeOrgTree");
		servletContext.setAttribute("collegeOrgTree", collegeOrgTree);
		Object collegeOrgMap = applicationContext.getBean("collegeOrgMap");
		servletContext.setAttribute("collegeOrgMap", collegeOrgMap);
		
		//教学地址信息
		Object collegeAddressTree = applicationContext.getBean("collegeAddressTree");
		servletContext.setAttribute("collegeAddressTree", collegeAddressTree);
		Object collegeAddressMap = applicationContext.getBean("collegeAddressMap");
		servletContext.setAttribute("collegeAddressMap", collegeAddressMap);
		
		//用户信息
		Object operatorMap = applicationContext.getBean("operatorMap");
		servletContext.setAttribute("operatorMap", operatorMap);
		
		//流程配置信息
		Object collegeProcessConfigsMap = applicationContext.getBean("collegeProcessConfigsMap");
		servletContext.setAttribute("collegeProcessConfigsMap", collegeProcessConfigsMap);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) 
	{

	}
}

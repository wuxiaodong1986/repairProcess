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
		
		//������Ϣ
		Object collegeOrgTree = applicationContext.getBean("collegeOrgTree");
		servletContext.setAttribute("collegeOrgTree", collegeOrgTree);
		Object collegeOrgMap = applicationContext.getBean("collegeOrgMap");
		servletContext.setAttribute("collegeOrgMap", collegeOrgMap);
		
		//��ѧ��ַ��Ϣ
		Object collegeAddressTree = applicationContext.getBean("collegeAddressTree");
		servletContext.setAttribute("collegeAddressTree", collegeAddressTree);
		Object collegeAddressMap = applicationContext.getBean("collegeAddressMap");
		servletContext.setAttribute("collegeAddressMap", collegeAddressMap);
		
		//�û���Ϣ
		Object operatorMap = applicationContext.getBean("operatorMap");
		servletContext.setAttribute("operatorMap", operatorMap);
		
		//����������Ϣ
		Object collegeProcessConfigsMap = applicationContext.getBean("collegeProcessConfigsMap");
		servletContext.setAttribute("collegeProcessConfigsMap", collegeProcessConfigsMap);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) 
	{

	}
}

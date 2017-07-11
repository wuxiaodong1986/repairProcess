package com.common.listener;

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
		//菜单信息map
		Object functionMap = applicationContext.getBean("idFunctionMap");
		servletContext.setAttribute("functionMap", functionMap);
		
		//配置信息
		Object configListMap = applicationContext.getBean("configListMap");
		servletContext.setAttribute("configListMap", configListMap);
		Object configMapMap = applicationContext.getBean("configMapMap");
		servletContext.setAttribute("configMapMap", configMapMap);
		
		//网站路径
		servletContext.setAttribute("webpath", servletContext.getInitParameter("webpath"));
		System.out.println("webpath "+servletContext.getInitParameter("webpath"));
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) 
	{

	}

}

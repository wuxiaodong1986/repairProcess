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
		//�˵���Ϣmap
		Object functionMap = applicationContext.getBean("idFunctionMap");
		servletContext.setAttribute("functionMap", functionMap);
		
		//������Ϣ
		Object configListMap = applicationContext.getBean("configListMap");
		servletContext.setAttribute("configListMap", configListMap);
		Object configMapMap = applicationContext.getBean("configMapMap");
		servletContext.setAttribute("configMapMap", configMapMap);
		
		//��վ·��
		servletContext.setAttribute("webpath", servletContext.getInitParameter("webpath"));
		System.out.println("webpath "+servletContext.getInitParameter("webpath"));
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) 
	{

	}

}

package com.common.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.common.dto.Function;
import com.college.dto.Operator;

/**
 * @author wuxiaodong
 *
 */
public class RoleFilter implements Filter
{
	private Map<String, Function> urlFunctionMap;
	
	@SuppressWarnings("unchecked")
	@Override
	public void init(FilterConfig filterConfig) throws ServletException 
	{
		WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
		
		urlFunctionMap = (Map<String, Function>)applicationContext.getBean("urlFunctionMap");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
    	
    	Operator operator = (Operator)httpServletRequest.getSession().getAttribute("operator");
    	Function function = (Function)httpServletRequest.getSession().getAttribute("function");
    	
    	String servletPath = httpServletRequest.getServletPath();
    	String[] servletPathSplit = servletPath.split("/");
    	String url = servletPathSplit[1];
    	
    	Function fullFunction = urlFunctionMap.get(url);
    	
    	if (null != fullFunction && !"01".equals(fullFunction.getP_id()))
    	{
    		if (null == operator)
        	{
        		request.getRequestDispatcher("/outError.htm?errorMessage=请先登录&returnUrl=operatorLogin.htm").forward(request, response);
        		return;
        	}
    		if ( null == function || !function.containsId(fullFunction.getId()))
    		{
        		request.getRequestDispatcher("/outError.htm?errorMessage=您没有该功能的权限").forward(request, response);
        		return;
    		}
    	}
    	
    	chain.doFilter(request, response);
	}

	@Override
	public void destroy() 
	{
		
	}
}

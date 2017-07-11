package com.common.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.common.dao.AutoLoggingDao;
import com.common.dto.AutoLogging;
import com.common.dto.Function;
import com.college.dto.Operator;

/**
 * @author wuxiaodong
 *
 */
public class LoggingFilter implements Filter
{
	private Map<String, Function> urlFunctionMap;
	
	private AutoLoggingDao autoLoggingDao;
	
	private Logger logger = Logger.getLogger("autoLogging");
	
	@SuppressWarnings("unchecked")
	public void init(FilterConfig filterConfig) throws ServletException
    {
		WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
		
		urlFunctionMap = (Map<String, Function>)applicationContext.getBean("urlFunctionMap");
		autoLoggingDao = (AutoLoggingDao)applicationContext.getBean("autoLoggingDao");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
    	AutoLogging autoLogging = new AutoLogging();
    	
    	HttpServletRequest httpServletRequest = (HttpServletRequest)request;
    	
    	Operator operator = (Operator)httpServletRequest.getSession().getAttribute("operator");
    	
    	if (null != operator)
    	{
    		autoLogging.setOid(operator.getId());
    		autoLogging.setName(operator.getName());
    		autoLogging.setUsername(operator.getUsername());
    	}
    	
    	String servletPath = httpServletRequest.getServletPath();
    	String[] servletPathSplit = servletPath.split("/");
    	String url = servletPathSplit[1];
    	autoLogging.setUrl(url);
    	
    	Function function = urlFunctionMap.get(url);
    	if (null != function)
    	{
    		autoLogging.setMid(function.getId());
    		autoLogging.setLogname(function.getLogname());
        	//打开菜单id
        	request.setAttribute("openMenuId", function.getP_id());
    	}
    	
    	//获取输入参数
    	Enumeration<String> parameterNames = request.getParameterNames();
    	StringBuffer parameters = new StringBuffer();
    	while (parameterNames.hasMoreElements())
    	{
    		String parameterName = parameterNames.nextElement();
    		String value = request.getParameter(parameterName);
    		
    		parameters.append(parameterName);
    		parameters.append("=");
    		parameters.append(value);
    		parameters.append("; ");
    	}
    	autoLogging.setParameters(parameters.toString());
    	
    	logger.info(autoLogging.toString());
    	autoLoggingDao.logging(autoLogging);
    	
    	chain.doFilter(request, response);
    }

    public void destroy()
    {

    }
}

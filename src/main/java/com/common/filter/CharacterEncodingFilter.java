package com.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author wuxiaodong
 *
 */
public class CharacterEncodingFilter implements Filter
{
    private String encoding;

    public void destroy()
    {
        // destroy code
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException,
        ServletException
    {
        request.setCharacterEncoding(encoding);
//        response.setContentType("text/html;charset=" + encoding);
        response.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException
    {
        this.encoding = filterConfig.getInitParameter("encoding");
    }
    
}

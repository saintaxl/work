/**
 * 
 */
package com.spring.demo;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

/**
 * @author Shawn
 * 
 */
public class CustomRestSecurityFilter extends GenericFilterBean {
	
	public Logger logger = LoggerFactory.getLogger(CustomRestSecurityFilter.class);

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		logger.error("this is filter chain {}",req.getRemoteHost());
		chain.doFilter(req, resp);
	}

}
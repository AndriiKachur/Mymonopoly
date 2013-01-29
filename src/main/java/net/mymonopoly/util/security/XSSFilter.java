package net.mymonopoly.util.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Wraps requests with {@link XSSRequestWrapper}.
 * 
 * @author Andrey K.
 * 
 */
public class XSSFilter implements Filter {

	private static final Log LOG = LogFactory.getLog(XSSFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOG.info("XSS protected!");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(new XSSRequestWrapper((HttpServletRequest) request), response);
	}

	@Override
	public void destroy() {
		LOG.info("XSS vulnurable!");
	}

}
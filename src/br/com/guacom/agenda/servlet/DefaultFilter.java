package br.com.guacom.agenda.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/static/*")
public class DefaultFilter implements Filter {
	private RequestDispatcher requestDispatcher;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		this.requestDispatcher.forward(request, response);
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.requestDispatcher = 
				filterConfig.getServletContext().getNamedDispatcher("default");
	}
	
	@Override
	public void destroy() {
		this.requestDispatcher = null;
	}
}
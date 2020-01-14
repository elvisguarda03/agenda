package br.com.guacom.agenda.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import br.com.guacom.agenda.dao.ConnectionFactory;

@WebFilter("/*")
public class ConnectionFilter implements Filter {
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("ConnectionFilter inicializado");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try (Connection conn = ConnectionFactory.getConnection()) {
			request.setAttribute("connection", conn);
			
			chain.doFilter(request, response);
		} catch (SQLException sqle) {
			throw new ServletException(sqle);
		}
	}
	
	@Override
	public void destroy() {
	}
}
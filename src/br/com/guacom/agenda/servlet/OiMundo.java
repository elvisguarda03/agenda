package br.com.guacom.agenda.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		value = "/oiMundo", 
		initParams = {
				@WebInitParam(name = "param1", value = "param1"),
				@WebInitParam(name = "param2", value = "param2")
		})
public class OiMundo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String param1;
	private String param2;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	
		this.param1 = config.getInitParameter("param1");
		this.param2 = config.getInitParameter("param2");
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
//		ServletConfig servletConfig = getServletConfig();
//		
//		this.param1 = servletConfig.getInitParameter("param1");
//		this.param2 = servletConfig.getInitParameter("param2");
		
		System.out.println(param1 + "\n" + param2);
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Primeira Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Oi mundo Servlet!</h1>");
		out.println("</body>");
		out.println("</html>");
	}
}

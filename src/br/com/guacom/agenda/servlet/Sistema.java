package br.com.guacom.agenda.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.guacom.agenda.controller.Controller;

@WebServlet("/")
public class Sistema extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String BASE_PATH = "br.com.guacom.agenda.controller.";
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getRequestURI().split("/")[2];
		String fqn = BASE_PATH + 
				Character.toUpperCase(action.charAt(0)) + action.substring(1) + "Controller";
		
		try {
			Class<?> clazz = Class.forName(fqn);
			Controller controller = (Controller) clazz.getConstructor().newInstance();
			
			String[] parts = controller.execute(request, response)
					.split(":");
			
			if (parts[0].equals("forward")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/" + parts[1]);
				dispatcher.forward(request, response);
				
				return;
			}
			
			response.sendRedirect(parts[1]);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException 
				| IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			String relativePath = request.getContextPath() + "/" + "listaContato";
			response.sendRedirect(relativePath);
			
			throw new ServletException(e);
		}
	}
}
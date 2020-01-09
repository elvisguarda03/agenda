package br.com.guacom.agenda.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/listaContato")
public class ListaContato extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/listaContatos.jsp");
		
//		ContatoDao dao = new ContatoDao();
		
//		request.setAttribute("contatos", dao.getContatos());

		dispatcher.forward(request, response);
	}
}

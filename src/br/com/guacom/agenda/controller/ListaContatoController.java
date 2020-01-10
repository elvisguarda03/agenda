package br.com.guacom.agenda.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.guacom.agenda.dao.ContatoDao;

public class ListaContatoController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = (Connection)request.getAttribute("connection");
		
		request.setAttribute("contatos", new ContatoDao(connection).getContatos());
		
		return "forward:listaContatos.jsp";
	}
}

package br.com.guacom.agenda.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.guacom.agenda.dao.ContatoDao;
import br.com.guacom.agenda.model.Contato;

public class RemoveContatoController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));

		Contato contato = new Contato();		
		contato.setId(id);

		Connection connection = (Connection)request.getAttribute("connection");
		
		ContatoDao dao = new ContatoDao(connection);
		dao.removeById(contato);

		request.getSession().setAttribute("message", "Contato excluído com sucesso!");

		return "redirect:listaContato/findAll";
	}
}

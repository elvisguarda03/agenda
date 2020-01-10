package br.com.guacom.agenda.controller;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.guacom.agenda.dao.ContatoDao;
import br.com.guacom.agenda.model.Contato;

public class AlteraContatoController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));

		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String endereco = request.getParameter("endereco");
		String data = request.getParameter("dataNascimento");
		
		Contato contato = new Contato();
		contato.setId(id);
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setEndereco(endereco);
		
		Calendar dataNascimento = Calendar.getInstance();
		
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			
			dataNascimento.setTime(date);
			
			contato.setDataNascimento(dataNascimento);
		} catch (ParseException pe) {
			throw new ServletException(pe);
		}
		
		Connection connection = (Connection)request.getAttribute("connection");
		
		ContatoDao dao = new ContatoDao(connection);
		dao.update(contato);
		
		request.getSession().setAttribute("contato", contato);
		
		return "redirect:listaContato/findAll";
	}
}
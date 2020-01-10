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

public class AdicionaContatoController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String endereco = request.getParameter("endereco");

		Calendar dataNascimento;

		Contato contato = new Contato();
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setEndereco(endereco);
		
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataNascimento"));

			dataNascimento = Calendar.getInstance();
			dataNascimento.setTime(date);

			contato.setDataNascimento(dataNascimento);
		} catch (ParseException pe) {
			request.setAttribute("contato", contato);
			return "forward:manipula-contato.jsp";
		}
		
		Connection connection = (Connection)request.getAttribute("connection");

		ContatoDao dao = new ContatoDao(connection);
		dao.adiciona(contato);

		request.setAttribute("name", contato.getNome());
		
		return "redirect:listaContato/findAll";
	}
}

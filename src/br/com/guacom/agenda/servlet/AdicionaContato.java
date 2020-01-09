package br.com.guacom.agenda.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.guacom.agenda.model.Contato;

//@WebServlet("/adicionaContato")
public class AdicionaContato extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		log("Iniciando a Servlet");
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String endereco = request.getParameter("endereco");

		Calendar dataNascimento;

		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataNascimento"));

			dataNascimento = Calendar.getInstance();
			dataNascimento.setTime(date);
		} catch (ParseException pe) {
			return;
		}

		Contato contato = new Contato();
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setEndereco(endereco);
		contato.setDataNascimento(dataNascimento);

//		ContatoDao dao = new ContatoDao();
//		dao.adiciona(contato);
		
		request.setAttribute("name", contato.getNome());
		
		request.getRequestDispatcher("/contato-adicionado.jsp").forward(request, response);
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
		log("Destruindo a servlet.");
	}
}
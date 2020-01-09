package br.com.guacom.agenda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;

import br.com.guacom.agenda.model.Contato;

public class ContatoDao {
	private Connection connection;

	public ContatoDao(Connection connection) {
		this.connection = connection;
	}

	public void adiciona(Contato contato) throws ServletException {
		final String	sql	=	"insert	into contatos (nome, email, endereco, dataNascimento) values (?,?,?,?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, contato.toDate());
			
			stmt.execute();
		} catch (SQLException sqle) {
			throw new ServletException(sqle);
		}
	}
	
	public List<Contato> getContatos() throws ServletException {
		final String sql = "select * from contatos;";
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			
			try (ResultSet resultSet = stmt.executeQuery()) {
				while (resultSet.next()) {
					contatos.add(buildContato(resultSet));
				}
			}
			return contatos;
		} catch (SQLException sqle) {
			throw new ServletException(sqle);
		}
		
	}
	
	public void removeById(Contato contato) throws ServletException {
		final String sql = "delete from contatos where id = ?";
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, contato.getId());
			
			log(sql);
			
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			throw new ServletException(sqle);
		}
	}


	public Contato findById(Contato contato) throws ServletException {
		final String sql = "select * from contatos where id = ?";
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setInt(1, contato.getId());
		
			try (ResultSet rs = stmt.executeQuery()) {
				Contato con = null;
				
				while (rs.next()) {
					con = buildContato(rs);
				}
				
				return con;
			}
		} catch (SQLException sqle) {
			throw new ServletException(sqle);
		}
	}
	
	public void update(Contato contato) throws ServletException {
		final String sql = "UPDATE contatos SET nome = ?, email = ?, endereco = ?, dataNascimento = ? where id = ?";
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, contato.toDate());
			stmt.setInt(5, contato.getId());
			
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			throw new ServletException(sqle);
		}
	}

	private Contato buildContato(ResultSet resultSet) throws SQLException {
		Integer id = resultSet.getInt("id");
		
		String nome = resultSet.getString("nome");
		String email = resultSet.getString("email");
		String endereco = resultSet.getString("endereco");
		
		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.setTimeInMillis(resultSet.getDate("dataNascimento").getTime());
		
		Contato contato = new Contato();
		
		contato.setId(id);
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setEndereco(endereco);
		contato.setDataNascimento(dataNascimento);
		
		return contato;
	}
	
	public void log(String info) {
		System.out.println(info);
	}
}
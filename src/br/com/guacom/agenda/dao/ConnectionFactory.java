package br.com.guacom.agenda.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(
					"jdbc:mysql://localhost/fj21?&serverTimezone=America/Bahia", "root", "eloah1106");
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
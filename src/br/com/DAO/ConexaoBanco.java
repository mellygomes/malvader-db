package br.com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {	
	private static final String URL = "jdbc:mysql://localhost:3306/malvaderdb";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	public static Connection conectar() throws SQLException {
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
			
		} catch (SQLException e){
			throw new RuntimeException("Erro ao conectar ao banco de dados: "+ e);
		}
	}
	
	public static void desconectar(Connection con) throws SQLException {
		try {
			con.close(); //fechando o ambiente de conexão
	        System.out.println("Execucao da Query fechada\n");
	        
		} catch (SQLException e){
			throw new RuntimeException("Erro ao conectar ao banco de dados: "+ e);
		}
	}
}

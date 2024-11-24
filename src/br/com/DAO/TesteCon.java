package br.com.DAO;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteCon {
	public static void main(String[] args) throws Exception {
		try (Connection con = ConexaoBanco.conectar()) {
									
			System.out.print("Conectado ao banco de dados!");
			ConexaoBanco.desconectar(con);
		} catch (SQLException e){
			e.printStackTrace();
		}	
	}
}

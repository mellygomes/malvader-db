package br.com.DAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.model.Relatorio;

public class RelatorioDAO {
	
	public static void save(Relatorio r) throws SQLException {
		String query = "INSERT INTO Relatorio(id_relatorio, tipo_relatorio, data_geracao, conteudo, fk_funcionario_id)"
				+ "values(null, ?,?,?,?)";

		int id_func = r.getId_funcionario();
		
		try (Connection con = ConexaoBanco.conectar()) {
			con.setAutoCommit(false);

			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, r.getTipo());
	        pst.setTimestamp(2, Timestamp.valueOf(r.getDataGeracao())); // Converter LocalDateTime para Timestamp
			pst.setString(3, r.getDados());
			pst.setInt(4, id_func);
			int rows = pst.executeUpdate();
			
			if (rows > 0) {
				con.commit();
			} else {
				System.out.print("Erro ao inserir relatorio");
				con.rollback();
			}
			
			ConexaoBanco.desconectar(con);
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Relatorio> listAll() throws SQLException {
		ArrayList<Relatorio> list = new ArrayList<>();
		String query = "SELECT * FROM Relatorio"; 
		
		try (Connection con = ConexaoBanco.conectar()) {
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Relatorio r = new Relatorio();
	            r = new Relatorio();
	            r.setId_funcionario(rs.getInt("fk_funcionario_id"));
	            r.setDataGeracao(rs.getTimestamp("data_geracao").toLocalDateTime());
	            r.setTipo(rs.getString("tipo_relatorio"));
	            r.setDados(rs.getString("conteudo"));
	            list.add(r);
			}
			
			ConexaoBanco.desconectar(con);
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return list;
	}
}

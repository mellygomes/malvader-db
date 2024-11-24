package testes;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import br.com.DAO.RelatorioDAO;
import br.com.model.Funcionario;
import br.com.model.Relatorio;

public class TesteRelatorio {
	public static void main(String[] args) throws SQLException {
		Relatorio r = new Relatorio();
//		Funcionario f = new Funcionario();
//		
//		r.setId_funcionario(10);
//		r.setDataGeracao(LocalDateTime.now());
//		r.setTipo("Abertura de conta");
//		r.setDados("Abriu a conta n 202020 no nome de Tal fulano");
//		RelatorioDAO.save(r);
		try {
			r.exportarParaExcel();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

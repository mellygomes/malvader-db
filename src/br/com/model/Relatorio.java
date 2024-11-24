package br.com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.DAO.RelatorioDAO;

public class Relatorio {
	private int id_funcionario;
	private String tipo;
	private LocalDateTime dataGeracao;
//	private List<String> dados;
	private String dados;
	
	//métodos getters e setters
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public LocalDateTime getDataGeracao() {
		return dataGeracao;
	}

	public void setDataGeracao(LocalDateTime dataGeracao) {
		this.dataGeracao = dataGeracao;
	}

	public String getDados() {
		return dados;
	}

	public void setDados(String dados) {
		this.dados = dados;
	}
	
	public int getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(int id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public ArrayList<Relatorio> gerarRelatorioGeral() throws SQLException {
        return RelatorioDAO.listAll();
	}
	
	public void exportarParaExcel() throws SQLException, IOException {
		String path = "./temp/relatorio.csv";
		FileWriter fw = null;
        
        try {
            fw = new FileWriter(path, StandardCharsets.ISO_8859_1, false);
            ArrayList<Relatorio> list = RelatorioDAO.listAll();
            fw.write("Tipo,DataGeração,Dados,idFuncionario\n");
            
            for (Relatorio r : list) {
            	fw.write(r.getTipo() + ",");
            	fw.write(r.getDataGeracao() + ",");
            	fw.write(r.getDados() + ",");
            	fw.write(r.getId_funcionario() + "\n");
            }

        } catch (IOException e){                              
            e.printStackTrace();
            
        } finally { 
            if (fw != null) {
                fw.close();
            }
        }
        
	}

}

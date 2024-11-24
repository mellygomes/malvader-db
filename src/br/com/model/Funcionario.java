package br.com.model;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import br.com.DAO.ClienteDAO;
import br.com.DAO.ContaDAO;
import br.com.DAO.FuncionarioDAO;
import br.com.DAO.RelatorioDAO;

public class Funcionario extends Usuario {
	private String codigoFuncionario;
	private String cargo;
	private String senha_funcionario;
	
	//métodos getters e setters
	public String getCodigo_funcionario() {
		return codigoFuncionario;
	}

	public void setCodigo_funcionario(String codigo) {
		this.codigoFuncionario = codigo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getSenha_funcionario() {
		return senha_funcionario;
	}

	public void setSenha_funcionario(String senha_funcionario) {
		this.senha_funcionario = senha_funcionario;
	}

	//métodos 
	public void abrirConta(Conta conta) {
		try {
			Cliente cliente = conta.getCliente();
			ClienteDAO.save(cliente);
			ContaDAO.save(conta);
			
			Relatorio r = new Relatorio();
			r.setId_funcionario(this.getId_usuario());
			r.setDataGeracao(LocalDateTime.now());
			r.setTipo("Abertura de conta");
			r.setDados("Abriu a conta n " + conta.getNumero_conta() + " para " + cliente.getNome_usuario());
			RelatorioDAO.save(r);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void encerrarConta(Conta conta) throws Exception {
		try {
			Cliente cliente = conta.getCliente();
			ClienteDAO.delete(cliente.getCpf_usuario()); //deleta cliente e conta associada
			
			Relatorio r = new Relatorio();
			r.setId_funcionario(this.getId_usuario());
			r.setDataGeracao(LocalDateTime.now());
			r.setTipo("Encerramento de conta");
			r.setDados("Encerrou a conta n " + conta.getNumero_conta() + " no nome de " + cliente.getNome_usuario());
			RelatorioDAO.save(r);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Conta consultarDadosConta(int numeroConta) throws Exception {
		return ContaDAO.findByNumero(numeroConta);
	}
	
	public Cliente consultarDadosCliente(int idCliente) throws Exception {
		return ClienteDAO.findById(idCliente);
	}
	
	public Funcionario consultarDadosFuncionario(int idFuncionario) throws Exception {
		return FuncionarioDAO.findById(idFuncionario);
	}
	
	public void alterarDadosConta(Conta conta) {
		 
	}
	
	public void alterarDadosCliente(Cliente cliente) {
		
	}
	
	public void cadastrarFuncionario(Funcionario funcionario) throws Exception {
		FuncionarioDAO.save(funcionario);
		
		Relatorio r = new Relatorio();
		r.setId_funcionario(this.getId_usuario());
		r.setDataGeracao(LocalDateTime.now());
		r.setTipo("Cadastro de funcionário");
		r.setDados("Cadastrou " + funcionario.getNome_usuario() + " CPF n " + funcionario.getCpf_usuario());
		RelatorioDAO.save(r);
	}
	
	public ArrayList<Relatorio> gerarRelatorioMovimentacao() throws SQLException {
		return RelatorioDAO.listAll();
	}
}

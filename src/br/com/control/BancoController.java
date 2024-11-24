package br.com.control;

import java.util.List;
import br.com.model.Conta;
import br.com.model.Funcionario;

public class BancoController {
	private List<Conta> contas;
	private List<Funcionario> funcionarios;
	
	//getters and setters
	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public void abrirConta(Conta conta) {
		
	}
	
	public void encerrarConta(int numeroConta) {
		
	}
	
	public Conta consultarConta(int numeroConta) {
		return null;
		
	}
	
	public void salvarDados() {
		
	}
	
	public void carregarDados() {
		
	}
}

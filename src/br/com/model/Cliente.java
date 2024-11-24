package br.com.model;

import br.com.DAO.ContaDAO;

public class Cliente extends Usuario {
	
	private String senha_cliente;
	
    public String getSenha_cliente() {
		return senha_cliente;
	}

	public void setSenha_cliente(String senha_cliente) {
		this.senha_cliente = senha_cliente;
	}

	//métodos
	public double consultarSaldo() {
		double saldo = 0;
        
        try {
            Conta conta = ContaDAO.findByClienteId(this.getId_usuario());
            saldo = conta.getSaldo_conta();
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        
        return saldo;
	}

	public boolean depositar(double valor) throws Exception {
        Conta conta = ContaDAO.findByClienteId(this.getId_usuario());
        System.out.println(" "+ this.getId_usuario());
        boolean confirma = ContaDAO.updateSaldoAdd(conta.getNumero_conta(), valor);
        return confirma;
    }

    public boolean sacar(double valor) throws Exception {
        Conta conta = ContaDAO.findByClienteId(this.getId_usuario());
        System.out.println(" "+ this.getId_usuario());
        boolean confirma = ContaDAO.updateSaldoMinus(conta.getNumero_conta(), valor);
        return confirma;
    }

	public String consultarExtrato() {
		return "";
	}

	public double consultarLimite() throws Exception {
        Conta conta = ContaDAO.findByClienteId(this.getId_usuario());
        if (conta instanceof ContaCorrente) {
        	ContaCorrente cc = (ContaCorrente) conta; 
        	return cc.getLimite();
        } else {
        	return -1;
        }
	}	
}

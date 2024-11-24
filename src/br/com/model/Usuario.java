package br.com.model;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.DAO.ClienteDAO;
import br.com.DAO.FuncionarioDAO;
import br.com.view.Login;

public abstract class Usuario {
	private int id_usuario;
	private String nome_usuario;
	private String cpf_usuario;
	private LocalDate nascimento_usuario;
	private String telefone_usuario;
	private String tipo_usuario;
	private String user_usuario;
	private Endereco endereco_usuario;
    
	//métodos getters e setters
    public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNome_usuario() {
		return nome_usuario;
	}

	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}

	public String getCpf_usuario() {
		return cpf_usuario;
	}

	public void setCpf_usuario(String cpf_usuario) {
		this.cpf_usuario = cpf_usuario;
	}

	public LocalDate getNascimento_usuario() {
		return nascimento_usuario;
	}

	public void setNascimento_usuario(LocalDate date) {
		this.nascimento_usuario = date;
	}

	public String getTelefone_usuario() {
		return telefone_usuario;
	}

	public void setTelefone_usuario(String telefone_usuario) {
		this.telefone_usuario = telefone_usuario;
	}

	public String getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(String tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}
	
	public String getUser_usuario() {
		return user_usuario;
	}

	public void setUser_usuario(String user_usuario) {
		this.user_usuario = user_usuario;
	}

	public Endereco getEndereco_usuario() {
		return endereco_usuario;
	}

	public void setEndereco_usuario(Endereco endereco_usuario) {
		this.endereco_usuario = endereco_usuario;
	}

	//métodos
	public boolean login(String senha) {
		boolean autenticacao = false;
		
		if (this.tipo_usuario.equals("FUNCIONARIO")) {
			try {
				Funcionario f = new Funcionario();
				f = FuncionarioDAO.findByUser(this.user_usuario); //o usuario deve ser setado antes da chamda do metodo
				autenticacao = (f.getSenha_funcionario().equals(String.valueOf(senha)));
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
		} else if (this.tipo_usuario.equals("CLIENTE")) {
			try {
				Cliente c = new Cliente();
				c = ClienteDAO.findByUser(this.user_usuario); //o usuario deve ser setado antes da chamda do metodo
				autenticacao = (c.getSenha_cliente().equals(String.valueOf(senha)));
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		return autenticacao;
	}

	public void logout() {
       //apagar a sessao salva em arquivo
		Login frame = new Login();
		frame.setVisible(true);
    }
    
    public ArrayList<String> consultarDados(String tipo_consulta) {
    	ArrayList<String> dados = new ArrayList<>();
    		
        if (tipo_consulta.equals("Conta")) {
        	
        } else if (tipo_consulta.equals("Cliente")) {
        	
        } else if (tipo_consulta.equals("Funcionario")) {
        	
        }
        return dados;
    }
    
}

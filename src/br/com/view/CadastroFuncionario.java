package br.com.view;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;

import javax.swing.*;

import br.com.DAO.FuncionarioDAO;
import br.com.model.Endereco;
import br.com.model.Funcionario;
import testes.UserSingleTon;

public class CadastroFuncionario extends JFrame {
	private static final long serialVersionUID = 1L;
	private final Funcionario loggeduser;
	
	public CadastroFuncionario(Funcionario user) {
		setTitle("Cadastro de funcionário");
		setSize(700, 660);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		this.loggeduser = user;
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(68, 65, 73));
		
		JLabel JLtitulo = new JLabel("Preencha as informações do funcionário");
		JLtitulo.setBounds(50, 10, 250, 40);
		JLtitulo.setForeground(Color.WHITE);
		panel.add(JLtitulo);
		
		JPanel panelcampos = new JPanel();
		panelcampos.setBackground(new Color(117, 114, 124));
		panelcampos.setLayout(null);
		panelcampos.setBounds(50, 50, 580, 300); 
		panel.add(panelcampos);
		panelcampos.setVisible(true);
		
		JLabel JLnome = new JLabel("Nome:");
		JLnome.setBounds(25, 20, 80, 20);
		JLnome.setForeground(Color.WHITE);
		panelcampos.add(JLnome);
		
		JTextField JTnome = new JTextField(100);
		JTnome.setBounds(70, 20, 400, 20);
		JTnome.setBackground(Color.white);
        panelcampos.add(JTnome);
        
        JLabel JLcpf = new JLabel("CPF:");
        JLcpf.setBounds(25, 60, 30, 20);
        JLcpf.setForeground(Color.WHITE);
        panelcampos.add(JLcpf);
		
		JTextField JTcpf = new JTextField(100);
		JTcpf.setBounds(70, 60, 200, 20);
		JTcpf.setBackground(Color.white);
		panelcampos.add(JTcpf);
		
		JLabel JLrg = new JLabel("RG:");
		JLrg.setBounds(300, 60, 30, 20);
		JLrg.setForeground(Color.WHITE);
        panelcampos.add(JLrg);
		
		JTextField JTrg = new JTextField(100);
		JTrg.setBounds(330, 60, 120, 20);
		JTrg.setBackground(Color.white);
		panelcampos.add(JTrg);
		
		JLabel JLnascimento = new JLabel("Data de nascimento:");
		JLnascimento.setBounds(25, 100, 150, 20);
		JLnascimento.setForeground(Color.WHITE);
        panelcampos.add(JLnascimento);
		
		JTextField JTnascimento = new JTextField(100);
		JTnascimento.setBounds(150, 100, 130, 20);
		JTnascimento.setBackground(Color.white);
		panelcampos.add(JTnascimento);
		
		JLabel JLtelefone = new JLabel("Telefone:");
		JLtelefone.setBounds(300, 100, 150, 20);
		JLtelefone.setForeground(Color.WHITE);
        panelcampos.add(JLtelefone);
        
        JTextField JTtelefone = new JTextField(100);
		JTtelefone.setBounds(370, 100, 130, 20);
		JTtelefone.setBackground(Color.white);
		panelcampos.add(JTtelefone);
		
		JLabel JLcargo = new JLabel("Cargo:");
		JLcargo.setBounds(25, 140, 150, 20);
		JLcargo.setForeground(Color.WHITE);
        panelcampos.add(JLcargo);
		
		JTextField JTcargo = new JTextField(100);
		JTcargo.setBounds(70, 140, 130, 20);
		JTcargo.setBackground(Color.white);
		panelcampos.add(JTcargo);
		
		JLabel JLcodigo = new JLabel("Codigo:");
		JLcodigo.setBounds(240, 140, 150, 20);
		JLcodigo.setForeground(Color.WHITE);
        panelcampos.add(JLcodigo);
		
		JTextField JTcodigo = new JTextField(100);
		JTcodigo.setBounds(300, 140, 130, 20);
		JTcodigo.setBackground(Color.white);
		panelcampos.add(JTcodigo);
		
		JLabel JLendtitulo = new JLabel("Endereço");
		JLendtitulo.setBounds(25, 170, 100, 20);
		JLendtitulo.setForeground(Color.WHITE);
        panelcampos.add(JLendtitulo);
		
        JLabel JLcep = new JLabel("CEP:");
        JLcep.setBounds(25, 210, 150, 20);
        JLcep.setForeground(Color.WHITE);
        panelcampos.add(JLcep);
		
		JTextField JTcep = new JTextField(100);
		JTcep.setBounds(70, 210, 130, 20);
		JTcep.setBackground(Color.white);
		panelcampos.add(JTcep);
		
		JLabel JLlocal = new JLabel("Local:");
		JLlocal.setBounds(220, 210, 150, 20);
		JLlocal.setForeground(Color.WHITE);
        panelcampos.add(JLlocal);
		
		JTextField JTlocal = new JTextField(100);
		JTlocal.setBounds(265, 210, 130, 20);
		JTlocal.setBackground(Color.white);
		panelcampos.add(JTlocal);
		
		JLabel JLnum = new JLabel("Numero:");
		JLnum.setBounds(420, 210, 150, 20);
		JLnum.setForeground(Color.WHITE);
        panelcampos.add(JLnum);
		
		JTextField JTnum = new JTextField(100);
		JTnum.setBounds(480, 210, 60, 20);
		JTnum.setBackground(Color.white);
		panelcampos.add(JTnum);
		
		JLabel JLbairro = new JLabel("Bairro:");
		JLbairro.setBounds(25, 250, 150, 20);
		JLbairro.setForeground(Color.WHITE);
        panelcampos.add(JLbairro);
		
		JTextField JTbairro = new JTextField(100);
		JTbairro.setBounds(70, 250, 130, 20);
		JTbairro.setBackground(Color.white);
		panelcampos.add(JTbairro);
		
		JLabel JLcidade = new JLabel("Cidade:");
		JLcidade.setBounds(220, 250, 150, 20);
		JLcidade.setForeground(Color.WHITE);
        panelcampos.add(JLcidade);
		
		JTextField JTcidade = new JTextField(100);
		JTcidade.setBounds(265, 250, 130, 20);
		JTcidade.setBackground(Color.white);
		panelcampos.add(JTcidade);
		
		JLabel JLuf = new JLabel("UF:");
		JLuf.setBounds(415, 250, 100, 20);
		JLuf.setForeground(Color.WHITE);
        panelcampos.add(JLuf);
		
		JTextField JTuf = new JTextField(100);
		JTuf.setBounds(460, 250, 90, 20);
		JTuf.setBackground(Color.white);
		panelcampos.add(JTuf);	
		
		//criacao da senha e usuario
		JLabel JLtitulos = new JLabel("Crie a senha e o nome de usuário do funcionário");
		JLtitulos.setBounds(100, 360, 290, 40);
		JLtitulos.setForeground(Color.WHITE);
		panel.add(JLtitulos);
		
		JPanel panels = new JPanel();
		panels.setBackground(new Color(117, 114, 124));
		panels.setLayout(null);
		panels.setBounds(100, 400, 500, 140); 
		panel.add(panels);
		panels.setVisible(true);
		
		JLabel JLuser = new JLabel("Usuário:");
		JLuser.setBounds(50, 30, 100, 20);
		JLuser.setForeground(Color.WHITE);
		panels.add(JLuser);
		
		JTextField JTuser = new JTextField(100);
		JTuser.setBounds(110, 30, 190, 20);
		JTuser.setBackground(Color.white);
		panels.add(JTuser);
		
		JLabel JLsenha= new JLabel("Senha:");
		JLsenha.setBounds(50, 70, 90, 20);
		JLsenha.setForeground(Color.WHITE);
		panels.add(JLsenha);
		
		JPasswordField JTsenha = new JPasswordField (100);
		JTsenha.setBounds(110, 70, 190, 20);
		JTsenha.setBackground(Color.white);
		JTsenha.setEchoChar('*');
		panels.add(JTsenha);
		
		JButton JBcadastrar = new JButton("Cadastrar");
		JBcadastrar.setBounds(350, 45, 90, 30);
		panels.add(JBcadastrar);
		
		JBcadastrar.addActionListener(e -> {
			Funcionario f = new Funcionario();
			f.setNome_usuario(JTnome.getText());
			f.setCpf_usuario(JTcpf.getText());
			f.setNascimento_usuario(LocalDate.parse(JTnascimento.getText()));
			f.setTelefone_usuario(JTtelefone.getText());
			f.setCargo(JTcargo.getText());
			f.setCodigo_funcionario(JTcodigo.getText());
			f.setTipo_usuario("Funcionário");
			f.setUser_usuario(JTuser.getText());
			f.setSenha_funcionario(String.valueOf(JTsenha.getPassword()));
			
			Endereco end = new Endereco();
			end.setCep(JTcargo.getText());
			end.setLocal(JTlocal.getText());
			end.setNumeroCasa(Integer.parseInt(JTnum.getText()));
			end.setBairro(JTbairro.getText());
			end.setCidade(JTcidade.getText());
			end.setUf(JTuf.getText());
			
			f.setEndereco_usuario(end);
			
			try {
				this.loggeduser.cadastrarFuncionario(f);
			} catch (Exception e1) {
				e1.printStackTrace();
			}			
		});
	}
}

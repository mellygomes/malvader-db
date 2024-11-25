package br.com.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.model.Cliente;
import br.com.model.Endereco;
import br.com.model.Funcionario;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;

public class ConsultaFuncionario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Funcionario loggeduser;
	private JTextField jTid;

	public ConsultaFuncionario(Funcionario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(700, 660);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.loggeduser = user;
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdDoFuncionrioque = new JLabel("ID do funcionário que deseja verificar: ");
		lblIdDoFuncionrioque.setBounds(41, 100, 219, 13);
		contentPane.add(lblIdDoFuncionrioque);
		
		jTid = new JTextField();
		jTid.setColumns(10);
		jTid.setBounds(270, 97, 35, 19);
		contentPane.add(jTid);
		
		JButton jBbuscar = new JButton("Buscar");
		jBbuscar.setBounds(315, 96, 85, 21);
		contentPane.add(jBbuscar);
		
		JLabel jLnao_encontrado = new JLabel();
		jLnao_encontrado.setBounds(410, 100, 208, 13);
		contentPane.add(jLnao_encontrado);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(41, 134, 577, 227);
		contentPane.add(panel);
		
		JLabel jLnome = new JLabel();
		jLnome.setBounds(25, 10, 283, 13);
		panel.add(jLnome);
		
		JLabel jLcpf = new JLabel();
		jLcpf.setBounds(363, 10, 185, 13);
		panel.add(jLcpf);
		
		JLabel jLnascimento = new JLabel();
		jLnascimento.setBounds(25, 33, 149, 13);
		panel.add(jLnascimento);
		
		JLabel jLtelefone = new JLabel();
		jLtelefone.setBounds(188, 33, 144, 13);
		panel.add(jLtelefone);
		
		JLabel jLusuario = new JLabel();
		jLusuario.setBounds(360, 33, 144, 13);
		panel.add(jLusuario);
		
		JLabel lblNewLabel_6 = new JLabel();
		lblNewLabel_6.setBounds(28, 100, 520, 13);
		panel.add(lblNewLabel_6);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(28, 119, 520, 79);
		panel.add(panel_1);
		
		JLabel jLcep = new JLabel();
		jLcep.setBounds(10, 10, 108, 13);
		panel_1.add(jLcep);
		
		JLabel jLlocal = new JLabel();
		jLlocal.setBounds(128, 10, 258, 13);
		panel_1.add(jLlocal);
		
		JLabel jLnumero = new JLabel();
		jLnumero.setBounds(414, 10, 96, 13);
		panel_1.add(jLnumero);
		
		JLabel jLcidade_uf = new JLabel();
		jLcidade_uf.setBounds(253, 43, 161, 13);
		panel_1.add(jLcidade_uf);
		
		JLabel jLbairro = new JLabel();
		jLbairro.setBounds(10, 43, 223, 13);
		panel_1.add(jLbairro);
		
		JLabel jLcargo = new JLabel();
		jLcargo.setBounds(25, 56, 172, 13);
		panel.add(jLcargo);
		
		JLabel jLcodigo = new JLabel();
		jLcodigo.setBounds(360, 56, 185, 13);
		panel.add(jLcodigo);
		
		JButton jBvoltar = new JButton();
		jBvoltar.setText("Voltar");
		jBvoltar.setBounds(280, 394, 93, 27);
		contentPane.add(jBvoltar);
		
		//action listeners
		jBvoltar.addActionListener(e -> {
			FuncionarioMenu frame = new FuncionarioMenu(this.loggeduser);
			frame.setVisible(true);
			this.dispose();
		});
		
		jBbuscar.addActionListener(e -> {
			try {
				Funcionario f = this.loggeduser.consultarDadosFuncionario(Integer.parseInt(jTid.getText()));
				if (f != null) {
					Endereco end = f.getEndereco_usuario();
					if (end != null) {
						
						jLnome.setText("Nome: " + f.getNome_usuario());
						jLcpf.setText("CPF: " + f.getCpf_usuario());
						jLnascimento.setText("Nascimento: " + String.valueOf(f.getNascimento_usuario()));
						jLtelefone.setText("Telefone: " + f.getTelefone_usuario());
						jLusuario.setText("Nome de usuário: " + f.getUser_usuario());
						jLcargo.setText("Cargo: " + f.getCargo());
						jLcodigo.setText("Código: " + f.getCodigo_funcionario());
						jLcep.setText("CEP: " + end.getCep());						
						jLlocal.setText("Logradouro: " + end.getLocal());
						jLnumero.setText("Número: " + String.valueOf(end.getNumeroCasa()));
						jLcidade_uf.setText("Cidade: " + end.getCidade() + " - " + end.getUf());
						jLbairro.setText("Bairro: " + end.getBairro());
						
					} else {
						System.out.print("Erro no endereco");
					}
					
				} else {
					jLnao_encontrado.setText("Nenhum resultado encontrado.");
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
	}

}

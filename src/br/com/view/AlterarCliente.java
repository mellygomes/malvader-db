package br.com.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.model.Cliente;
import br.com.model.Endereco;
import br.com.model.Funcionario;
import javax.swing.JLabel;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class AlterarCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Funcionario loggeduser;
	private JTextField jTid;
	private JTextField jTtelefone;
	private JTextField jTcep;
	private JTextField jTlocal;
	private JTextField jTnumero;
	private JTextField jTcidade;
	private JTextField jTuf;
	private JTextField jTbairro;
	
	public AlterarCliente(Funcionario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(700, 660);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.loggeduser = user;
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID do cliente que deseja verificar: ");
		lblNewLabel.setBounds(43, 51, 219, 13);
		contentPane.add(lblNewLabel);
		
		jTid = new JTextField();
		jTid.setColumns(10);
		jTid.setBounds(261, 48, 35, 19);
		contentPane.add(jTid);
		
		JButton jBbuscar = new JButton("Buscar");
		jBbuscar.setBounds(306, 47, 85, 21);
		contentPane.add(jBbuscar);
		
		JLabel jLnao_encontrado = new JLabel();
		jLnao_encontrado.setBounds(410, 51, 208, 13);
		contentPane.add(jLnao_encontrado);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(41, 74, 577, 326);
		contentPane.add(panel);
		
		JLabel jLnome = new JLabel();
		jLnome.setBounds(28, 28, 283, 13);
		panel.add(jLnome);
		
		JLabel jLcpf = new JLabel();
		jLcpf.setBounds(348, 28, 185, 13);
		panel.add(jLcpf);
		
		JLabel jLnascimento = new JLabel();
		jLnascimento.setBounds(28, 51, 172, 13);
		panel.add(jLnascimento);
		
		JLabel jLusuario = new JLabel();
		jLusuario.setBounds(402, 51, 131, 13);
		panel.add(jLusuario);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(28, 140, 486, 154);
		panel.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblNewLabel_10 = new JLabel("Telefone de contato: ");
		lblNewLabel_10.setBounds(10, 10, 121, 13);
		panel_2.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Endereço");
		lblNewLabel_11.setBounds(10, 49, 45, 13);
		panel_2.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("CEP: ");
		lblNewLabel_12.setBounds(10, 75, 45, 13);
		panel_2.add(lblNewLabel_12);
		
		JLabel JLlocal = new JLabel("Logradouro: ");
		JLlocal.setBounds(145, 75, 109, 13);
		panel_2.add(JLlocal);
		
		JLabel lblNewLabel_14 = new JLabel("Numero: ");
		lblNewLabel_14.setBounds(379, 75, 63, 13);
		panel_2.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("Bairro: ");
		lblNewLabel_15.setBounds(332, 109, 45, 13);
		panel_2.add(lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel("Cidade: ");
		lblNewLabel_16.setBounds(10, 109, 63, 13);
		panel_2.add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("Estado:");
		lblNewLabel_17.setBounds(209, 109, 45, 13);
		panel_2.add(lblNewLabel_17);
		
		jTtelefone = new JTextField();
		jTtelefone.setBounds(113, 7, 115, 19);
		panel_2.add(jTtelefone);
		jTtelefone.setColumns(10);
		
		jTcep = new JTextField();
		jTcep.setColumns(10);
		jTcep.setBounds(38, 72, 87, 19);
		panel_2.add(jTcep);
		
		jTlocal = new JTextField();
		jTlocal.setColumns(10);
		jTlocal.setBounds(237, 72, 115, 19);
		panel_2.add(jTlocal);
		
		jTnumero = new JTextField();
		jTnumero.setColumns(10);
		jTnumero.setBounds(426, 72, 34, 19);
		panel_2.add(jTnumero);
		
		jTcidade = new JTextField();
		jTcidade.setColumns(10);
		jTcidade.setBounds(65, 106, 125, 19);
		panel_2.add(jTcidade);
		
		jTuf = new JTextField();
		jTuf.setColumns(10);
		jTuf.setBounds(267, 106, 55, 19);
		panel_2.add(jTuf);
		
		jTbairro = new JTextField();
		jTbairro.setColumns(10);
		jTbairro.setBounds(379, 106, 78, 19);
		panel_2.add(jTbairro);
		
		JLabel lblNewLabel_1 = new JLabel("Alterar dados:");
		lblNewLabel_1.setBounds(28, 112, 359, 13);
		panel.add(lblNewLabel_1);
		
		JButton jBalterar = new JButton("Alterar");
		jBalterar.setBounds(203, 422, 93, 27);
		contentPane.add(jBalterar);
		
		JButton jBvoltar = new JButton("Voltar");
		jBvoltar.setBounds(357, 422, 93, 27);
		contentPane.add(jBvoltar);
		
		//action listeners
		
		jBvoltar.addActionListener(e -> {
			FuncionarioMenu frame = new FuncionarioMenu(this.loggeduser);
			frame.setVisible(true);
			this.dispose();
		});
		
		jBbuscar.addActionListener(e -> {
			try {
				Cliente c = this.loggeduser.consultarDadosCliente(Integer.parseInt(jTid.getText()));
				if (c != null) {
					Endereco end = c.getEndereco_usuario();
					if (end != null) {
						
						jLnome.setText("Nome: " + c.getNome_usuario());
						jLcpf.setText("CPF: " + c.getCpf_usuario());
						jLnascimento.setText("Nascimento: " + String.valueOf(c.getNascimento_usuario()));
						jLusuario.setText("Nome de usuário: " + c.getUser_usuario());
						jTtelefone.setText(c.getTelefone_usuario());
						jTcep.setText(end.getCep());						
						jTlocal.setText(end.getLocal());
						jTnumero.setText(String.valueOf(end.getNumeroCasa()));
						jTcidade.setText(end.getCidade());
						jTuf.setText(end.getUf());
						jTbairro.setText(end.getBairro());
						
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
		
		jBalterar.addActionListener(e -> {
			Cliente c = new Cliente();
			Endereco end = new Endereco();
			
			c.setId_usuario(Integer.parseInt(jTid.getText()));
			c.setTelefone_usuario(jTtelefone.getText());
			end.setLocal(jTlocal.getText());
			end.setNumeroCasa(Integer.parseInt(jTnumero.getText()));
			end.setCep(jTcep.getText());
			end.setUf(jTuf.getText());
			end.setCidade(jTcidade.getText());
			end.setBairro(jTbairro.getText());
			c.setEndereco_usuario(end);
			
			try {
				this.loggeduser.alterarDadosCliente(c);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
	}
		
}

package br.com.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.MaskFormatter;

import br.com.model.Cliente;
import br.com.model.ContaPoupanca;
import br.com.model.Endereco;
import br.com.model.Funcionario;

import java.text.ParseException;
import java.time.LocalDate;
public class AbrirPoupanca extends JFrame {

	private static final long serialVersionUID = 1L;
	private final Funcionario loggeduser;
	private JPanel contentPane;
	private JTextField jTnome;
	private JTextField jTlocal;
	private JTextField jTcidade;
	private JTextField jTuf;
	private JTextField jTbairro;
	private JTextField jTnumero_casa;
	private JTextField jTusuario;
	private JTextField jTsenha;
	private JTextField jTrendimento;

	 //Create the frame.
	public AbrirPoupanca(Funcionario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(700, 660);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.loggeduser = user;

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(77, 50, 506, 471);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("Dados do cliente para o qual a conta será aberta: ");
		lblNewLabel_1.setBounds(10, 10, 359, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Dados da conta:");
		lblNewLabel_2.setBounds(10, 281, 210, 13);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 304, 486, 96);
		panel.add(panel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Numero da conta:");
		lblNewLabel_3.setBounds(50, 18, 118, 13);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Agencia:");
		lblNewLabel_4.setBounds(166, 18, 104, 13);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("Taxa de rendemento:");
		lblNewLabel_6.setBounds(290, 18, 156, 13);
		panel_1.add(lblNewLabel_6);
		
		MaskFormatter mascaraCpf = null;
		MaskFormatter mascaraData = null;
		MaskFormatter mascaraTelefone = null;
		MaskFormatter mascaraCep = null;
		MaskFormatter mascaraNumero = null;
		MaskFormatter mascaraAgencia = null;
		
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraData = new MaskFormatter("####-##-##");		
			mascaraTelefone = new MaskFormatter("(##)#####-####");
			mascaraCep = new MaskFormatter("##.###-###");
			mascaraNumero = new MaskFormatter("######-#");
			mascaraAgencia = new MaskFormatter("####");
			mascaraCep.setPlaceholderCharacter('_');
            mascaraTelefone.setPlaceholderCharacter('_');
            mascaraCpf.setPlaceholderCharacter('_');
            mascaraData.setPlaceholderCharacter('_');
            mascaraNumero.setPlaceholderCharacter('_');
            mascaraAgencia.setPlaceholderCharacter('_');

            
		} catch (ParseException e) {
			System.err.println("Erro na formatação: " + e.getMessage());
			e.printStackTrace();
		}
		
		JFormattedTextField jFTnumero = new JFormattedTextField(mascaraCpf);
		jFTnumero.setBounds(50, 41, 79, 19);
		panel_1.add(jFTnumero);
		
		JFormattedTextField JFTagencia = new JFormattedTextField(mascaraAgencia);
		JFTagencia.setBounds(166, 41, 84, 19);
		panel_1.add(JFTagencia);
		
		jTrendimento = new JTextField();
		jTrendimento.setBounds(290, 41, 96, 19);
		panel_1.add(jTrendimento);
		jTrendimento.setColumns(10);
		
		JButton jTabrir_conta = new JButton("Abrir conta");
		jTabrir_conta.setBounds(106, 424, 105, 21);
		panel.add(jTabrir_conta);
		
		JButton jBcancelar = new JButton("Cancelar");
		jBcancelar.setBounds(283, 424, 99, 21);
		panel.add(jBcancelar);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(10, 33, 486, 168);
		panel.add(panel_2);
		
		JLabel lblNewLabel_7 = new JLabel("Nome: ");
		lblNewLabel_7.setBounds(10, 10, 45, 13);
		panel_2.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("CPF: ");
		lblNewLabel_8.setBounds(348, 10, 36, 13);
		panel_2.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Data de nascimento");
		lblNewLabel_9.setBounds(10, 47, 138, 13);
		panel_2.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Telefone de contato: ");
		lblNewLabel_10.setBounds(241, 47, 121, 13);
		panel_2.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Endereço");
		lblNewLabel_11.setBounds(10, 82, 45, 13);
		panel_2.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("CEP: ");
		lblNewLabel_12.setBounds(10, 111, 45, 13);
		panel_2.add(lblNewLabel_12);
		
		JLabel JLlocal = new JLabel("Logradouro: ");
		JLlocal.setBounds(136, 111, 109, 13);
		panel_2.add(JLlocal);
		
		JLabel lblNewLabel_14 = new JLabel("Numero: ");
		lblNewLabel_14.setBounds(379, 111, 78, 13);
		panel_2.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("Bairro: ");
		lblNewLabel_15.setBounds(332, 145, 45, 13);
		panel_2.add(lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel("Cidade: ");
		lblNewLabel_16.setBounds(10, 145, 63, 13);
		panel_2.add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("Estado:");
		lblNewLabel_17.setBounds(183, 145, 45, 13);
		panel_2.add(lblNewLabel_17);
		
		jTnome = new JTextField();
		jTnome.setColumns(10);
		jTnome.setBounds(48, 7, 290, 19);
		panel_2.add(jTnome);
		
		JFormattedTextField jFTcpf = new JFormattedTextField(mascaraCpf);
		jFTcpf.setBounds(379, 7, 97, 19);
		panel_2.add(jFTcpf);
		
		JFormattedTextField jFTnascimento = new JFormattedTextField(mascaraData);
		jFTnascimento.setToolTipText("AAAA-MM-DD");
		jFTnascimento.setBounds(138, 44, 70, 19);
		panel_2.add(jFTnascimento);
		
		JFormattedTextField jFTtelefone = new JFormattedTextField(mascaraTelefone);
		jFTtelefone.setBounds(367, 44, 109, 19);
		panel_2.add(jFTtelefone);
		
		JFormattedTextField jFTcep = new JFormattedTextField(mascaraCep);
		jFTcep.setBounds(42, 108, 84, 19);
		panel_2.add(jFTcep);
		
		jTlocal = new JTextField();
		jTlocal.setColumns(10);
		jTlocal.setBounds(211, 108, 151, 19);
		panel_2.add(jTlocal);
		
		jTcidade = new JTextField();
		jTcidade.setColumns(10);
		jTcidade.setBounds(65, 142, 109, 19);
		panel_2.add(jTcidade);
		
		jTuf = new JTextField();
		jTuf.setColumns(10);
		jTuf.setBounds(226, 142, 96, 19);
		panel_2.add(jTuf);
		
		jTbairro = new JTextField();
		jTbairro.setColumns(10);
		jTbairro.setBounds(380, 142, 96, 19);
		panel_2.add(jTbairro);
		
		jTnumero_casa = new JTextField();
		jTnumero_casa.setColumns(10);
		jTnumero_casa.setBounds(431, 108, 45, 19);
		panel_2.add(jTnumero_casa);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(10, 234, 486, 37);
		panel.add(panel_3);
		
		JLabel jLsenha = new JLabel("Senha: ");
		jLsenha.setBounds(293, 13, 87, 13);
		panel_3.add(jLsenha);
		
		JLabel lblNewLabel_20 = new JLabel("Nome de usuário: ");
		lblNewLabel_20.setBounds(41, 13, 109, 13);
		panel_3.add(lblNewLabel_20);
		
		jTusuario = new JTextField();
		jTusuario.setColumns(10);
		jTusuario.setBounds(145, 10, 122, 19);
		panel_3.add(jTusuario);
		
		jTsenha = new JTextField();
		jTsenha.setColumns(10);
		jTsenha.setBounds(343, 10, 96, 19);
		panel_3.add(jTsenha);
		
		JLabel lblNewLabel_18 = new JLabel("Crie senha e usuário para o cliente");
		lblNewLabel_18.setBounds(10, 211, 275, 13);
		panel.add(lblNewLabel_18);
		
		jTabrir_conta.addActionListener(e -> {
			try{
                ContaPoupanca cp = new ContaPoupanca();
				Cliente cl = new Cliente();
		        Endereco en = new Endereco();

		        cl.setNome_usuario(jTnome.getText());
		        cl.setCpf_usuario(String.valueOf(jFTcpf.getText().replaceAll("\\D", "")));
		        cl.setNascimento_usuario(LocalDate.parse(jFTnascimento.getText()));
		        cl.setSenha_cliente(jTsenha.getText());
		        cl.setTelefone_usuario(String.valueOf(jFTtelefone.getText().replaceAll("\\D", "")));
		        cl.setTipo_usuario("CLIENTE");
		        cl.setUser_usuario(jTusuario.getText());

		        en.setBairro(jTbairro.getText());
		        en.setUf(jTuf.getText());
		        en.setCep(String.valueOf(jFTcep.getText().replaceAll("\\D", "")));
		        en.setLocal(jTlocal.getText());
		        en.setNumeroCasa(Integer.parseInt(jTnumero_casa.getText()));
		        en.setCidade(jTcidade.getText());
		        cl.setEndereco_usuario(en);
		        
		        cp.setTipo_conta("POUPANCA");
		        cp.setTaxaRendimento(Double.parseDouble(jTrendimento.getText()));
		        cp.setAgencia_conta(JFTagencia.getText().replaceAll("\\D", ""));
		        cp.setNumero_conta(Integer.parseInt(jFTnumero.getText().replaceAll("\\D", "")));
		        cp.setSaldo_conta(0);
		        
		        cp.setCliente(cl);
		        
		        try {
		        	this.loggeduser.abrirConta(cp);
		        	
		        	JOptionPane.showMessageDialog(this, "Conta berta com sucesso!");
                    FuncionarioMenu frame = new FuncionarioMenu(this.loggeduser);
                    frame.setVisible(true);
                    this.dispose();
                } catch (Exception ex) {
                	ex.printStackTrace();
                	JOptionPane.showMessageDialog(this, "Não foi possível abrir a conta devido a algum erro interno.", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
		        
			 } catch (Exception ex) {
             	ex.printStackTrace();
             }
		});
		
		jBcancelar.addActionListener(e -> {
			FuncionarioMenu frame = new FuncionarioMenu(this.loggeduser);
			frame.setVisible(true);
			this.dispose();
			
		});
	}

}

package br.com.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.DAO.ClienteDAO;
import br.com.model.Cliente;
import br.com.model.Endereco;
import br.com.model.Funcionario;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;

public class ConsultaCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Funcionario loggeduser;
	private JTextField jTid;

	public ConsultaCliente(Funcionario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(700, 660);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.loggeduser = user;
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(49, 157, 577, 227);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel jLnome = new JLabel();
		jLnome.setBounds(28, 28, 283, 13);
		panel.add(jLnome);
		
		JLabel jLcpf = new JLabel();
		jLcpf.setBounds(348, 28, 185, 13);
		panel.add(jLcpf);
		
		JLabel jLnascimento = new JLabel();
		jLnascimento.setBounds(28, 51, 172, 13);
		panel.add(jLnascimento);
		
		JLabel jLtelefone = new JLabel();
		jLtelefone.setBounds(196, 51, 144, 13);
		panel.add(jLtelefone);
		
		JLabel jLusuario = new JLabel();
		jLusuario.setBounds(402, 51, 131, 13);
		panel.add(jLusuario);
		
		JLabel lblNewLabel_6 = new JLabel("Endereço:");
		lblNewLabel_6.setBounds(28, 100, 520, 13);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel = new JLabel("ID do cliente que deseja verificar: ");
		lblNewLabel.setBounds(49, 123, 219, 13);
		contentPane.add(lblNewLabel);
		
		jTid = new JTextField();
		jTid.setBounds(278, 120, 35, 19);
		contentPane.add(jTid);
		jTid.setColumns(10);
		
		JButton jBbuscar = new JButton("Buscar");
		jBbuscar.setBounds(323, 119, 85, 21);
		contentPane.add(jBbuscar);
		
		JButton jBvoltar = new JButton("Voltar");
		jBvoltar.setBounds(288, 417, 93, 27);
		contentPane.add(jBvoltar);
		
		JLabel jLnao_encontrado = new JLabel();
		jLnao_encontrado.setBounds(418, 123, 208, 13);
		contentPane.add(jLnao_encontrado);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(28, 119, 520, 79);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel jLcep = new JLabel();
		jLcep.setBounds(10, 10, 77, 13);
		panel_1.add(jLcep);
		
		JLabel jLlocal = new JLabel();
		jLlocal.setBounds(186, 10, 201, 13);
		panel_1.add(jLlocal);
		
		JLabel jLnumero = new JLabel();
		jLnumero.setBounds(377, 10, 115, 13);
		panel_1.add(jLnumero);
		
		JLabel jLcidade_uf = new JLabel();
		jLcidade_uf.setBounds(253, 43, 115, 13);
		panel_1.add(jLcidade_uf);
		
		JLabel jLbairro = new JLabel();
		jLbairro.setBounds(10, 43, 172, 13);
		panel_1.add(jLbairro);
		
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
						jLtelefone.setText("Telefone: " + c.getTelefone_usuario());
						jLusuario.setText("Nome de usuário: " + c.getUser_usuario());
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

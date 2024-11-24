package br.com.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.model.Cliente;
import br.com.model.Conta;
import br.com.model.ContaCorrente;
import br.com.model.ContaPoupanca;
import br.com.model.Funcionario;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;

public class ConsultaConta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Funcionario loggeduser;
	private JTextField jTnumero;

	public ConsultaConta(Funcionario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(700, 660);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumeroDaConta = new JLabel("Numero da conta que deseja verificar: ");
		lblNumeroDaConta.setBounds(46, 109, 219, 13);
		contentPane.add(lblNumeroDaConta);
		
		jTnumero = new JTextField();
		jTnumero.setColumns(10);
		jTnumero.setBounds(247, 106, 85, 19);
		contentPane.add(jTnumero);
		
		JButton jBbuscar = new JButton("Buscar");
		jBbuscar.setBounds(353, 105, 85, 21);
		contentPane.add(jBbuscar);
		
		JLabel jLnao_encontrado = new JLabel();
		jLnao_encontrado.setBounds(468, 109, 208, 13);
		contentPane.add(jLnao_encontrado);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(46, 143, 577, 227);
		contentPane.add(panel);
		
		JLabel jLnome = new JLabel("");
		jLnome.setBounds(119, 39, 219, 13);
		panel.add(jLnome);
		
		JLabel jLcpf = new JLabel("");
		jLcpf.setBounds(348, 39, 185, 13);
		panel.add(jLcpf);
		
		JLabel lblNewLabel_6 = new JLabel("Dados da conta:");
		lblNewLabel_6.setBounds(28, 73, 520, 13);
		panel.add(lblNewLabel_6);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(28, 96, 520, 79);
		panel.add(panel_1);
		
		JLabel jLtipo = new JLabel("");
		jLtipo.setBounds(10, 10, 133, 13);
		panel_1.add(jLtipo);
		
		JLabel jLnumero_conta = new JLabel("");
		jLnumero_conta.setBounds(186, 10, 201, 13);
		panel_1.add(jLnumero_conta);
		
		JLabel jLagencia = new JLabel("");
		jLagencia.setBounds(377, 10, 115, 13);
		panel_1.add(jLagencia);
		
		JLabel jLlimite_ou_rendimento = new JLabel("");
		jLlimite_ou_rendimento.setBounds(186, 43, 151, 13);
		panel_1.add(jLlimite_ou_rendimento);
		
		JLabel jLsaldo_atual = new JLabel("");
		jLsaldo_atual.setBounds(10, 43, 172, 13);
		panel_1.add(jLsaldo_atual);
		
		JLabel jLvencimento = new JLabel("");
		jLvencimento.setBounds(377, 43, 133, 13);
		panel_1.add(jLvencimento);
		
		JLabel lblNewLabel = new JLabel("Dados do titular:");
		lblNewLabel.setBounds(28, 5, 138, 13);
		panel.add(lblNewLabel);
		
		JButton jBvoltar = new JButton("Voltar");
		jBvoltar.setBounds(285, 403, 93, 27);
		contentPane.add(jBvoltar);
		this.loggeduser = user;
		
		//action listeners
		jBvoltar.addActionListener(e -> {
			FuncionarioMenu frame = new FuncionarioMenu(this.loggeduser);
			frame.setVisible(true);
			this.dispose();
		});
		
		jBbuscar.addActionListener(e -> {
			try {
				Conta conta = this.loggeduser.consultarDadosConta(Integer.parseInt(jTnumero.getText()));
				Cliente c = conta.getCliente();
				if (c != null) {
				
					jLnome.setText("Nome: " + c.getNome_usuario());
					jLcpf.setText("CPF: " + c.getCpf_usuario());
					jLtipo.setText("Tipo: " + conta.getTipo_conta());
					jLnumero_conta.setText("Número: " + String.valueOf(conta.getNumero_conta()));
					jLagencia.setText("Agência: " + conta.getAgencia_conta());
					jLsaldo_atual.setText("Saldo atual: " + String.valueOf(conta.getSaldo_conta()));
					if (conta instanceof ContaCorrente) {
						ContaCorrente cc = (ContaCorrente) conta;
						jLlimite_ou_rendimento.setText("Limite: " + String.valueOf(cc.getLimite()));
						jLvencimento.setText("Vemcimento: " + String.valueOf(cc.getDataVencimento()));
					} else if (conta instanceof ContaPoupanca) {
						ContaPoupanca cp = (ContaPoupanca) conta;
						jLlimite_ou_rendimento.setText("Taxa de rendimento: " + String.valueOf(cp.getTaxaRendimento() + "%"));
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

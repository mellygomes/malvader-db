package br.com.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.DAO.ContaDAO;
import br.com.model.Cliente;
import br.com.model.Conta;
import br.com.model.ContaCorrente;
import br.com.model.ContaPoupanca;
import br.com.model.Endereco;
import br.com.model.Funcionario;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;

public class AlterarConta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Funcionario loggeduser;
	private JTextField jTnumero;
	private JTextField jTlimite;
	private JTextField jTtaxa;
	private JTextField jTvencimento;
	
	public AlterarConta(Funcionario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(700, 660);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.loggeduser = user;
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Número da conta que deseja verificar: ");
		lblNewLabel.setBounds(55, 63, 219, 13);
		contentPane.add(lblNewLabel);
		
		jTnumero = new JTextField();
		jTnumero.setColumns(10);
		jTnumero.setBounds(273, 60, 70, 19);
		contentPane.add(jTnumero);
		
		JButton jBbuscar = new JButton("Buscar");
		jBbuscar.setBounds(372, 59, 85, 21);
		contentPane.add(jBbuscar);
		
		JLabel jLnao_encontrado = new JLabel();
		jLnao_encontrado.setBounds(467, 63, 181, 13);
		contentPane.add(jLnao_encontrado);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(53, 86, 577, 326);
		contentPane.add(panel);
		
		JLabel jLnome = new JLabel();
		jLnome.setBounds(28, 28, 283, 13);
		panel.add(jLnome);
		
		JLabel jLcpf = new JLabel();
		jLcpf.setBounds(348, 28, 185, 13);
		panel.add(jLcpf);
		
		JLabel jLnumero_conta = new JLabel();
		jLnumero_conta.setBounds(28, 51, 172, 13);
		panel.add(jLnumero_conta);
		
		JLabel jLagencia = new JLabel();
		jLagencia.setBounds(402, 51, 142, 13);
		panel.add(jLagencia);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(28, 140, 486, 154);
		panel.add(panel_2);
		
		JLabel jLlimite = new JLabel("Limite");
		jLlimite.setBounds(84, 23, 45, 13);
		panel_2.add(jLlimite);
		
		JLabel jLtaxa = new JLabel("Taxa de rencimento:");
		jLtaxa.setBounds(84, 86, 115, 13);
		panel_2.add(jLtaxa);
		
		JLabel JLdata = new JLabel("Data de vencimento:");
		JLdata.setBounds(235, 23, 109, 13);
		panel_2.add(JLdata);
		
		jTlimite = new JTextField();
		jTlimite.setText((String) null);
		jTlimite.setColumns(10);
		jTlimite.setBounds(84, 46, 63, 19);
		panel_2.add(jTlimite);
		
		jTtaxa = new JTextField();
		jTtaxa.setText((String) null);
		jTtaxa.setColumns(10);
		jTtaxa.setBounds(84, 109, 87, 19);
		panel_2.add(jTtaxa);
		
		jTvencimento = new JTextField();
		jTvencimento.setText((String) null);
		jTvencimento.setColumns(10);
		jTvencimento.setBounds(235, 46, 115, 19);
		panel_2.add(jTvencimento);
		
		JLabel lblNewLabel_1 = new JLabel("Alterar dados:");
		lblNewLabel_1.setBounds(28, 112, 359, 13);
		panel.add(lblNewLabel_1);
		
		JLabel jLsaldo_atual = new JLabel();
		jLsaldo_atual.setBounds(210, 51, 101, 13);
		panel.add(jLsaldo_atual);
		
		JLabel jLtipo = new JLabel("");
		jLtipo.setBounds(28, 85, 121, 13);
		panel.add(jLtipo);
		
		JButton jBalterar = new JButton("Alterar");
		jBalterar.setBounds(215, 434, 93, 27);
		contentPane.add(jBalterar);
		
		JButton jBvoltar = new JButton("Voltar");
		jBvoltar.setBounds(369, 434, 93, 27);
		contentPane.add(jBvoltar);

		//action listeners		
		jBvoltar.addActionListener(e -> {
			FuncionarioMenu frame = new FuncionarioMenu(this.loggeduser);
			frame.setVisible(true);
			this.dispose();
		});
		
		
		jLtaxa.setVisible(false);
		jTtaxa.setVisible(false);
		
		jBbuscar.addActionListener(e -> {
			try {
				Conta conta = ContaDAO.findByNumero(Integer.parseInt(jTnumero.getText()));
				
				if (conta != null) {
					
					Cliente c = conta.getCliente();
						jLnome.setText("Nome: " + c.getNome_usuario());
						jLcpf.setText("CPF: " + c.getCpf_usuario());
						jLnumero_conta.setText("Número: " + String.valueOf(conta.getNumero_conta()));
						jLagencia.setText("Agencia: " + conta.getAgencia_conta());
						jLsaldo_atual.setText("Saldo atual: " + String.valueOf(conta.getSaldo_conta()));
						jLtipo.setText(conta.getTipo_conta());
						
						if (conta instanceof ContaCorrente) {
							ContaCorrente cc = (ContaCorrente) conta;
							
							jTlimite.setText(String.valueOf(cc.getLimite()));
							jTvencimento.setText(String.valueOf(cc.getDataVencimento()));
						} else if (conta instanceof ContaPoupanca) {
							ContaPoupanca cp = (ContaPoupanca) conta;
							jTlimite.setVisible(false);
							jLlimite.setVisible(false);
							jTvencimento.setVisible(false);
							JLdata.setVisible(false);
							jLtaxa.setVisible(true);
							jLtaxa.setBounds(84, 25, 115, 13);
							jTtaxa.setVisible(true);
							jTtaxa.setBounds(84, 50, 87, 19);
							jTtaxa.setText(String.valueOf(cp.getTaxaRendimento()));
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

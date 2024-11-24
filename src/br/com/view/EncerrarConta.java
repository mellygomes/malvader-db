package br.com.view;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import br.com.DAO.ContaDAO;
import br.com.model.Cliente;
import br.com.model.Conta;
import br.com.model.Funcionario;
import javax.swing.border.EtchedBorder;
import java.awt.Font;

public class EncerrarConta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Funcionario loggeduser;
	private JTextField jTnumero;
	private Conta conta;
	
	public EncerrarConta(Funcionario user) {
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
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 72, 666, 78);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel jLtitular = new JLabel();
		jLtitular.setBounds(10, 20, 249, 13);
		panel.add(jLtitular);
		
		JLabel jLcpf = new JLabel();
		jLcpf.setBounds(290, 20, 207, 13);
		panel.add(jLcpf);
		
		JLabel jLtipo = new JLabel();
		jLtipo.setBounds(10, 43, 145, 13);
		panel.add(jLtipo);
		
		JLabel jLnumero = new JLabel();
		jLnumero.setBounds(179, 43, 173, 13);
		panel.add(jLnumero);
		
		JLabel jLagencia = new JLabel();
		jLagencia.setBounds(312, 43, 173, 13);
		panel.add(jLagencia);
		
		JLabel lblNewLabel = new JLabel("Numero da conta a ser encerrado: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 45, 291, 17);
		contentPane.add(lblNewLabel);
		
		JLabel jLsaldo = new JLabel();
		jLsaldo.setBounds(507, 43, 118, 13);
		panel.add(jLsaldo);
		
		jTnumero = new JTextField();
		jTnumero.setBounds(318, 43, 96, 19);
		contentPane.add(jTnumero);
		jTnumero.setColumns(10);
		
		JButton jBbuscar = new JButton("Buscar");
		jBbuscar.setBounds(472, 41, 85, 21);
		contentPane.add(jBbuscar);
		
		JButton jBencerrar = new JButton("Encerrar");
		jBencerrar.setBounds(239, 171, 85, 21);
		contentPane.add(jBencerrar);
		
		JButton jBcancelar = new JButton("Cancelar");
		jBcancelar.setBounds(363, 171, 85, 21);
		contentPane.add(jBcancelar);
				
		jBbuscar.addActionListener(e -> {			
	        	try {
	        		System.out.print("jT: " +jTnumero);
					this.conta = ContaDAO.findByNumero(Integer.parseInt(jTnumero.getText()));
					Cliente cl = conta.getCliente();
					
					jLtitular.setText("Titular: " + cl.getNome_usuario());
					jLcpf.setText("CPF: " + cl.getCpf_usuario());
					jLnumero.setText("Numero: " + conta.getNumero_conta());
					jLagencia.setText(conta.getAgencia_conta());
					jLsaldo.setText("Saldo atual: " + String.valueOf(conta.getSaldo_conta()));
					String tipo = conta.getTipo_conta();
					if (tipo.equals("POUPANCA")) {
						jLtipo.setText("Tipo: conta poupança");
					} else if (tipo.equals("CORRENTE")) {
						jLtipo.setText("Tipo: conta corrente");	
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		});
		

		jBcancelar.addActionListener(e -> {
			FuncionarioMenu frame = new FuncionarioMenu(this.loggeduser);
			frame.setVisible(true);
			this.dispose();
		});
		
		jBencerrar.addActionListener(e -> {
			Object[] options = {"Encerrar conta","Cancelar"};
	        int n = JOptionPane.showOptionDialog(this,
	            "Atenção! Todos os registros da conta e do usuário cliente serão permanentemente excluídos.",
	            "Confirmar encerramento da conta",
	            JOptionPane.YES_NO_CANCEL_OPTION,
	            JOptionPane.DEFAULT_OPTION,
	            null,
	            options,
	            options[1]);

	        if (n == 0) {
	        	try {
					this.loggeduser.encerrarConta(this.conta);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
	        	
	        	JOptionPane.showMessageDialog(this, "Finalizado!", "A conta foi encerrada.", JOptionPane.INFORMATION_MESSAGE);
	        	FuncionarioMenu frame = new FuncionarioMenu(this.loggeduser);
				frame.setVisible(true);
				this.dispose();
	        } else if (n == 1) {
	        	FuncionarioMenu frame = new FuncionarioMenu(this.loggeduser);
				frame.setVisible(true);
				this.dispose();
	        }
		});
		
	}

}

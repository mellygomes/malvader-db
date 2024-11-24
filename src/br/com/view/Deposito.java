package br.com.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.DAO.ContaDAO;
import br.com.model.Cliente;
import br.com.model.Conta;
import br.com.model.Relatorio;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Deposito extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Cliente loggeduser;
	private JTextField jTvalor_deposito;
	private Double saldo;
	
	public Deposito(Cliente user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(700, 660);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.loggeduser = user;

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDepsito = new JLabel("Depósito");
		lblDepsito.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepsito.setFont(new Font("Arial", Font.BOLD, 28));
		lblDepsito.setBounds(254, 72, 139, 51);
		contentPane.add(lblDepsito);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(107, 149, 453, 372);
		contentPane.add(panel);
		
		JLabel jLsaldo = new JLabel("0.0");
		jLsaldo.setHorizontalAlignment(SwingConstants.CENTER);
		jLsaldo.setFont(new Font("Arial", Font.PLAIN, 14));
		jLsaldo.setBounds(160, 41, 128, 13);
		panel.add(jLsaldo);
		
		try {
			Conta conta = ContaDAO.findByClienteId(this.loggeduser.getId_usuario());
			this.saldo = conta.getSaldo_conta();
			jLsaldo.setText(String.valueOf(this.saldo));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JLabel lblNewLabel_1 = new JLabel("Valor atual:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(23, 40, 192, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Valor a ser depositado:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(56, 123, 180, 13);
		panel.add(lblNewLabel_2);
		
		jTvalor_deposito = new JTextField();
		jTvalor_deposito.setColumns(10);
		jTvalor_deposito.setBounds(234, 117, 144, 19);
		panel.add(jTvalor_deposito);
		
		JButton jBconfirma = new JButton("Confirmar");
		jBconfirma.setBounds(114, 199, 85, 21);
		panel.add(jBconfirma);
		
		JButton jBcancelar = new JButton("Cancelar");
		jBcancelar.setBounds(244, 199, 85, 21);
		panel.add(jBcancelar);
		
		jBcancelar.addActionListener(e -> {
			ClienteMenu frame = new ClienteMenu(this.loggeduser);
			frame.setVisible(true);
			this.dispose();
		});
		
		jBconfirma.addActionListener(e -> {
			if (jTvalor_deposito != null) {
				Double valor_deposito = Double.parseDouble(jTvalor_deposito.getText());
//				if (valor_debito <= this.saldo) {
					try {
						this.loggeduser.depositar(valor_deposito);
						
						Object[] options = {"Outro depósito","Voltar ao menu"};
				        int n = JOptionPane.showOptionDialog(this,
				            "Deseja realizar outro depósito?",
				            "Depósito realizado com sucesso!",
				            JOptionPane.YES_NO_CANCEL_OPTION,
				            JOptionPane.DEFAULT_OPTION,
				            null,
				            options,
				            options[1]);
				        
				        if (n == 1) {
				        	ClienteMenu frame = new ClienteMenu(this.loggeduser);
							frame.setVisible(true);
							this.dispose();
				        }
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
//				} else {
//					JOptionPane.showMessageDialog(this, "Saldo disponível insuficiente para este saque.", "Saque não autorizado!", JOptionPane.ERROR_MESSAGE);
//				}
			} else {
				JOptionPane.showMessageDialog(this, "É necessário informar o valor do saque.", "Campo necessário", JOptionPane.ERROR_MESSAGE);
				jTvalor_deposito.requestFocus();
			}
		});
	}

}

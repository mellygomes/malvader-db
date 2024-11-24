package br.com.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.DAO.ContaDAO;
import br.com.model.Cliente;
import br.com.model.Conta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Saque extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Cliente loggeduser;
	private JTextField jTvalor_debito;
	private Double saldo = (double) 0;
	
	public Saque(Cliente user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(700, 660);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.loggeduser = user;
		
		JLabel lblNewLabel = new JLabel("Saque");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 28));
		lblNewLabel.setBounds(281, 43, 91, 51);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(114, 120, 453, 372);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel jLsaldo = new JLabel("0.0");
		jLsaldo.setFont(new Font("Arial", Font.PLAIN, 14));
		jLsaldo.setHorizontalAlignment(SwingConstants.CENTER);
		jLsaldo.setBounds(160, 41, 128, 13);
		panel.add(jLsaldo);
		
		JLabel lblNewLabel_1 = new JLabel("Valor disponível:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(23, 40, 192, 13);
		panel.add(lblNewLabel_1);
	
		try {
			Conta conta = ContaDAO.findByClienteId(this.loggeduser.getId_usuario());
			this.saldo = conta.getSaldo_conta();
			jLsaldo.setText(String.valueOf(this.saldo));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JLabel lblNewLabel_2 = new JLabel("Valor a ser depitado:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(56, 123, 180, 13);
		panel.add(lblNewLabel_2);
		
		jTvalor_debito = new JTextField();
		jTvalor_debito.setBounds(234, 117, 144, 19);
		panel.add(jTvalor_debito);
		jTvalor_debito.setColumns(10);
		
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
			if (jTvalor_debito != null) {
				Double valor_debito = Double.parseDouble(jTvalor_debito.getText());
				if (valor_debito <= this.saldo) {
					try {
						this.loggeduser.sacar(valor_debito);
						
						Object[] options = {"Outro saque","Voltar ao menu"};
				        int n = JOptionPane.showOptionDialog(this,
				            "Deseja realizar outro saque?",
				            "Saque realizado com sucesso!",
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
				} else {
					JOptionPane.showMessageDialog(this, "Saldo disponível insuficiente para este saque.", "Saque não autorizado!", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "É necessário informar o valor do saque.", "Campo necessário", JOptionPane.ERROR_MESSAGE);
				jTvalor_debito.requestFocus();
			}
		});
	}

}

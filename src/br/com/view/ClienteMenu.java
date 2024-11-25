package br.com.view;

import java.awt.EventQueue;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.model.Cliente;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.Color;

public class ClienteMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Cliente loggeduser;
	
	public ClienteMenu(Cliente user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(800, 660);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.loggeduser = user;
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 786, 22);
		contentPane.add(menuBar);
		
		JMenu jMopcoes = new JMenu("Mais opções");
		menuBar.add(jMopcoes);
		
		JMenuItem jMIlogout = new JMenuItem("Logout");
		jMopcoes.add(jMIlogout);
		
		JMenuItem jMIlogoutsair = new JMenuItem("Logout e sair");
		jMopcoes.add(jMIlogoutsair);
		
		//dividir o nome pra mostrar apenas o primeiro e segundo
		String[] elemento = this.loggeduser.getNome_usuario().split(" ");
        String nome = elemento[0];
        String sobrenome = elemento[1];
		
		JLabel jLsaudacao = new JLabel();
		jLsaudacao.setText("Olá, " + nome +" "+ sobrenome);
		jLsaudacao.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		jLsaudacao.setBounds(122, 62, 346, 25);
		contentPane.add(jLsaudacao);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(159, 103, 438, 400);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JToggleButton jTBvisualizarsaldo = new JToggleButton("Mostrar saldo");
		jTBvisualizarsaldo.setBounds(151, 80, 130, 28);
		panel.add(jTBvisualizarsaldo);
		
		JLabel jLsaldo = new JLabel("XXX");
		jLsaldo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jLsaldo.setHorizontalAlignment(SwingConstants.CENTER);
		jLsaldo.setBounds(151, 49, 130, 13);
		panel.add(jLsaldo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 135, 438, 265);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton jBsaque = new JButton("Saque");
		jBsaque.setBounds(59, 44, 85, 28);
		panel_1.add(jBsaque);
		
		JButton jBdeposito = new JButton("Depósito");
		jBdeposito.setBounds(266, 44, 85, 28);
		panel_1.add(jBdeposito);
		
		JButton jBextrato = new JButton("Extrato");
		jBextrato.setBounds(59, 152, 85, 28);
		panel_1.add(jBextrato);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(247, 247, 247));
		panel_2.setBounds(59, 71, 85, 57);
		panel_1.add(panel_2);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(255, 255, 255));
		panel_2_1.setBounds(266, 176, 85, 57);
		panel_1.add(panel_2_1);
		
		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setBackground(new Color(255, 255, 255));
		panel_2_1_1.setBounds(59, 176, 85, 57);
		panel_1.add(panel_2_1_1);
		
		JPanel panel_2_1_1_1 = new JPanel();
		panel_2_1_1_1.setBackground(new Color(247, 247, 247));
		panel_2_1_1_1.setBounds(266, 71, 85, 57);
		panel_1.add(panel_2_1_1_1);
		
		JButton jBlimite = new JButton("Limite");
		jBlimite.setBounds(266, 152, 85, 28);
		panel_1.add(jBlimite);
		
        JButton jBsair = new JButton("Sair");
        jBsair.setBackground(new Color(254, 235, 237));
        jBsair.setBounds(337, 525, 85, 21);
        contentPane.add(jBsair);
		
		jMIlogoutsair.addActionListener(e -> {
			this.loggeduser.logout();
			System.exit(0);
		});
		
		jMIlogout.addActionListener(e -> {
			this.loggeduser.logout();
			this.dispose();
		});
		
		jBsair.addActionListener(e -> {
			System.exit(0);
		});

		jBsaque.addActionListener(e -> {
			Saque frame = new Saque(this.loggeduser);
			frame.setVisible(true);
			this.dispose();
		});
		
		jBdeposito.addActionListener(e -> {
			Deposito frame = new Deposito(this.loggeduser);
			frame.setVisible(true);
			this.dispose();
		});
		
		jBextrato.addActionListener(e -> {
			Extrato frame = new Extrato(this.loggeduser);
			frame.setVisible(true);
			this.dispose();
		});
		
		jTBvisualizarsaldo.addActionListener(e -> {
			 AbstractButton abstractButton = (AbstractButton)e.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if (selected) {
		            String senha = JOptionPane.showInputDialog(this, "Digite a senha de"
		                + "usuário para vizializar o saldo.", "Confirmar usuário.", JOptionPane.INFORMATION_MESSAGE);
		                
		            if (this.loggeduser.getSenha_cliente().equals(senha)) {  
		                Double saldo = this.loggeduser.consultarSaldo();
	                    jLsaldo.setText(" "+ saldo);
	                    jTBvisualizarsaldo.setText("Ocultar saldo");
	                    jLsaldo.setText("Você ainda não possui uma conta :(");
		                
		                jLsaldo.setText("R$ "+ saldo);
		                jTBvisualizarsaldo.setText("Ocultar saldo");
		            } else {
		                JOptionPane.showMessageDialog(this, "Senha incorreta!", "Tente novamente.", JOptionPane.ERROR_MESSAGE);
		            }
		        } else {
		        	jTBvisualizarsaldo.setText("Mostrar saldo");
		            jLsaldo.setText("XXX");
		        }
		});
		
		jBlimite.addActionListener(e -> {
			String senha = JOptionPane.showInputDialog(this, "Digite a senha de"
                + "usuário para vizializar o limite disponível.", "Confirmar usuário.", JOptionPane.INFORMATION_MESSAGE);
            if (this.loggeduser.getSenha_cliente().equals(senha)) {
				try {
					Double limite = this.loggeduser.consultarLimite();
					
					if (limite >= 0) {
		            	JOptionPane.showMessageDialog(this, "R$ " + limite, "Limite disponível: ", JOptionPane.INFORMATION_MESSAGE);
					} else {
		                JOptionPane.showMessageDialog(this, "Não há limite disponível para o tipo de conta poupança.", "Sem limite disponível!", JOptionPane.ERROR_MESSAGE);
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
            } else {
                JOptionPane.showMessageDialog(this, "Senha incorreta!", "Tente novamente.", JOptionPane.ERROR_MESSAGE);
            }
		});
		
	}

}

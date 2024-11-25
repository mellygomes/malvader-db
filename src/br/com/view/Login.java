package br.com.view;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

import br.com.DAO.ClienteDAO;
import br.com.DAO.FuncionarioDAO;
import br.com.model.Cliente;
import br.com.model.Funcionario;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.io.IOException;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPasswordField jPsenha;
	private JTextField jTuser;

	//Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the frame.
	public Login() {
		setFont(new Font("Times New Roman", Font.PLAIN, 14));
		setSize(800, 660);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(68, 65, 73));
		
		JPanel panelcampos = new JPanel();
		panelcampos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelcampos.setBounds(-7, -6, 793, 629);
		panel.add(panelcampos);
		panelcampos.setLayout(null);
				
		JLabel lblNewLabel = new JLabel("Efetuar login");
		lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
        lblNewLabel.setBounds(340, 78, 110, 19);
        panelcampos.add(lblNewLabel);
		
		jPsenha = new JPasswordField();
		jPsenha.setBounds(340, 234, 110, 19);
		jPsenha.setEchoChar('*');
		panelcampos.add(jPsenha);
		
		JLabel lblNewLabel_1_1 = new JLabel("Usuario");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(218, 160, 53, 17);
		panelcampos.add(lblNewLabel_1_1);
		
		jTuser = new JTextField();
		jTuser.setBounds(301, 161, 247, 19);
		panelcampos.add(jTuser);
		jTuser.setColumns(10);
        jTuser.requestFocus();
		
		JButton jBlogin = new JButton("Login");
		jBlogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jBlogin.setBounds(340, 374, 97, 31);
		panelcampos.add(jBlogin);
		
		JLabel lblSenha_1 = new JLabel("Senha");
		lblSenha_1.setForeground(new Color(0, 0, 0));
		lblSenha_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha_1.setBounds(269, 233, 61, 17);
		panelcampos.add(lblSenha_1);
		
		JRadioButton jRcliente = new JRadioButton("Cliente");
		jRcliente.setForeground(new Color(0, 0, 0));
		jRcliente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jRcliente.setBounds(477, 309, 85, 21);
		panelcampos.add(jRcliente);
		
		JRadioButton jRfuncionario = new JRadioButton("Funcionário");
		jRfuncionario.setForeground(new Color(0, 0, 0));
		jRfuncionario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jRfuncionario.setBounds(201, 309, 103, 21);
		panelcampos.add(jRfuncionario);
		
		ButtonGroup g = new ButtonGroup();
		g.add(jRcliente);
		g.add(jRfuncionario);
		
		JLabel JLtitle = new JLabel("Login");
		JLtitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JLtitle.setBounds(376, 83, 53, 26);
		panel.add(JLtitle);
		
		jBlogin.addActionListener(e -> {
			if (jRfuncionario.isSelected()) {
	            Funcionario f = new Funcionario();
	            f.setUser_usuario(jTuser.getText());
	            f.setTipo_usuario("FUNCIONARIO");
	            
	            boolean login = f.login(String.valueOf(jPsenha.getPassword()));

	            if (login) {
	                try {
	                    Funcionario funcionariologado = FuncionarioDAO.findByUser(f.getUser_usuario());
	                    FuncionarioMenu ftela = new FuncionarioMenu(funcionariologado);                    
	                    ftela.setVisible(true);
	                    this.dispose();
	                    
	                } catch (Exception ex) {
	                	ex.printStackTrace();
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "O login falhou! Verifique a senha e usuário inserido.");
	            }

	        } else if (jRcliente.isSelected()) {
	            Cliente c = new Cliente();
	            c.setUser_usuario(jTuser.getText());
	            c.setTipo_usuario("CLIENTE");
	            boolean login = c.login(String.valueOf(jPsenha.getPassword()));
	            
	            if (login) {                
	                try {
	                    Cliente clientelogado = ClienteDAO.findByUser(c.getUser_usuario());
	                    ClienteMenu ftela = new ClienteMenu(clientelogado);                    
	                    ftela.setVisible(true);
	                    this.dispose();
	                    
	                } catch (Exception ex) {
	                	ex.printStackTrace();
	                }
	                
	            } else {
	                JOptionPane.showMessageDialog(null, "O login falhou! Verifique a senha e usuário inserido.");
	            }

	        } else {
	            JOptionPane.showMessageDialog(null, "É necessário selecionar o tipo de usuário!");
	        }
			
		});
		
		JPopupMenu popupMenu = new JPopupMenu();
		
		JMenuItem mntmFuncionario = new JMenuItem("Funcionário");
		popupMenu.add(mntmFuncionario);
		
		JMenuItem mntmCliente = new JMenuItem("Cliente");
		popupMenu.add(mntmCliente);
		
		
		mntmFuncionario.addActionListener(e -> {
//			this.dispose();
//			CadastroFuncionario frame = new CadastroFuncionario();
//			try {
//				//frame.cadastro("Cadastro de funcionário");
//				//frame.setVisible(true);
//			} catch (FontFormatException x) {
//				x.printStackTrace();
//			} catch (IOException x) {
//				x.printStackTrace();
//			}
		});
	}
}

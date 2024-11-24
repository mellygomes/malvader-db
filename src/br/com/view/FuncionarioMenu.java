package br.com.view;

import br.com.model.Funcionario;
import br.com.model.Relatorio;

import java.awt.Font;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FuncionarioMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Funcionario loggeduser;

	//Create the frame.
	public FuncionarioMenu(Funcionario user) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 760);
		setSize(800, 660);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.loggeduser = user; //-> set the logged user in this screen

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel jLsaudacao = new JLabel();
		jLsaudacao.setText("Olá, "+ this.loggeduser.getNome_usuario());
		jLsaudacao.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		jLsaudacao.setBounds(123, 75, 346, 25);
		contentPane.add(jLsaudacao);
		
		JPanel panel = new JPanel();
		panel.setBounds(238, 98, 346, 459);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton jBabertura_conta = new JButton("Abertura de Conta");
		jBabertura_conta.setBounds(0, 41, 346, 35);
		panel.add(jBabertura_conta);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(jBabertura_conta, popupMenu);
		
		JMenuItem jMIpoupanca = new JMenuItem("Poupança");
		popupMenu.add(jMIpoupanca);
		
		JMenuItem jMIcorrente = new JMenuItem("Corrente");
		popupMenu.add(jMIcorrente);
		
		JButton jBencerrar_conta = new JButton("Encerramento de conta");
		jBencerrar_conta.setBounds(0, 109, 346, 35);
		panel.add(jBencerrar_conta);
		
		JButton jBconsultar_dados = new JButton("Consultar Dados");
		jBconsultar_dados.setBounds(0, 171, 346, 35);
		panel.add(jBconsultar_dados);
		
		JPopupMenu popupMenu_1 = new JPopupMenu();
		addPopup(jBconsultar_dados, popupMenu_1);
		
		JMenuItem jMIfuncionario = new JMenuItem("Concultar dados de funcionário");
		popupMenu_1.add(jMIfuncionario);
		
		JMenuItem jMIcliente = new JMenuItem("Consultar dados de cliente");
		popupMenu_1.add(jMIcliente);
		
		JMenuItem jMIconta = new JMenuItem("Consultar dados de conta");
		popupMenu_1.add(jMIconta);
		
		JButton jBalterar_dados = new JButton("Alterar Dados");
		jBalterar_dados.setBounds(0, 240, 346, 35);
		panel.add(jBalterar_dados);
		
		JButton btncadastro_funcionario = new JButton("Cadastrar novo funcionário");
		btncadastro_funcionario.setBounds(0, 310, 346, 35);
		panel.add(btncadastro_funcionario);
		
		JButton jBgerar_relatorio = new JButton("Gerar relatório");
		jBgerar_relatorio.setBounds(0, 380, 346, 35);
		panel.add(jBgerar_relatorio);
		
		JButton jBsair = new JButton("Sair");
		jBsair.setBounds(137, 438, 85, 21);
		panel.add(jBsair);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 786, 22);
		contentPane.add(menuBar);
		
		JMenu jMenubar = new JMenu("Mais opções");
		menuBar.add(jMenubar);
		
		JMenuItem jMIlogout = new JMenuItem("Logout");
		jMenubar.add(jMIlogout);
		
		JMenuItem jMIlogoutsai = new JMenuItem("Logout e sair");
		jMenubar.add(jMIlogoutsai);
		
		//action events
		jMIlogoutsai.addActionListener(e -> {
			this.loggeduser.logout();
			System.exit(0);
		});
		
		jMIlogout.addActionListener(e -> {
			this.loggeduser.logout();
			this.dispose();
		});
		
		jBsair.addActionListener(e -> {
			System.exit(0); //sair mas mater a sessao logada
		});
		
		jMIcorrente.addActionListener(e -> {
			AbrirCorrente frame = new AbrirCorrente(this.loggeduser);
			frame.setVisible(true);
			this.dispose();
		});
		
		jMIpoupanca.addActionListener(e -> {
			AbrirPoupanca frame = new AbrirPoupanca(this.loggeduser);
			frame.setVisible(true);
			this.dispose();
		});
		
		jBencerrar_conta.addActionListener(e -> {
			EncerrarConta frame = new EncerrarConta(this.loggeduser);
			frame.setVisible(true);
			this.dispose();
		});
		
		jMIcliente.addActionListener(e -> {
			ConsultaCliente frame = new ConsultaCliente(this.loggeduser);
			frame.setVisible(true);
			this.dispose();
		});
		
		jMIfuncionario.addActionListener(e -> {
			ConsultaFuncionario frame = new ConsultaFuncionario(this.loggeduser);
			frame.setVisible(true);
			this.dispose();
		});

		jMIconta.addActionListener(e -> {
			ConsultaConta frame = new ConsultaConta(this.loggeduser);
			frame.setVisible(true);
			this.dispose();
		});
		
		btncadastro_funcionario.addActionListener(e -> {
			CadastroFuncionario frame = new CadastroFuncionario(this.loggeduser);
			frame.setVisible(true);
			this.dispose();
		});
		
		jBgerar_relatorio.addActionListener(e -> {
			//senha
			int n = JOptionPane.showConfirmDialog(this, "Deseja exportar o relatório geral (em .csv) para o exel?", "Confirmar"
					+ " exportação.", JOptionPane.OK_CANCEL_OPTION);
			
			if (n == 0) {
				Relatorio r = new Relatorio();
				try {
					r.exportarParaExcel();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), 250, 35);
			}
		});
	}	
}

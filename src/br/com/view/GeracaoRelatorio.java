package br.com.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.model.Funcionario;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTable;

public class GeracaoRelatorio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Funcionario loggeduser;
	private JTable table;
	
	public GeracaoRelatorio(Funcionario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(600, 560);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(70, 47, 449, 454);
		contentPane.add(panel);
		
		table = new JTable();
		panel.add(table);
		
		JLabel lblNewLabel = new JLabel("Relatório de movimentação recente:");
		lblNewLabel.setBounds(70, 24, 355, 13);
		contentPane.add(lblNewLabel);
		this.loggeduser = user;
		
		
	}
}

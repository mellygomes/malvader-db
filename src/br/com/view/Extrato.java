package br.com.view;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.DAO.ContaDAO;
import br.com.model.Cliente;
import br.com.model.Conta;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class Extrato extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Cliente loggeduser;
	private JTable table;
	
	public Extrato(Cliente user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(700, 660);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(124, 112, 401, 263);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Extrato dos últimos 30 dias:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(124, 79, 290, 13);
		contentPane.add(lblNewLabel);
		this.loggeduser = user;
		
		JButton jBvoltar = new JButton("Voltar");
		jBvoltar.setBounds(289, 404, 85, 21);
		contentPane.add(jBvoltar);
		
		jBvoltar.addActionListener(e -> {
			ClienteMenu frame = new ClienteMenu(this.loggeduser);
			frame.setVisible(true);
			this.dispose();
		});
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Transação tipo", "Data", "Valor"
			}
		));
		
        DefaultTableModel tablepesque = (DefaultTableModel) table.getModel();  
		Conta conta;
		try {
		    conta = ContaDAO.findByClienteId(this.loggeduser.getId_usuario());
		    ArrayList<String> extrato = ContaDAO.extratoById(conta.getId_conta());

		    // Preenche os dados na tabela
		    for (int i = 0; i < extrato.size(); i += 3) { // Incrementa de 3 em 3
		        String tipo = extrato.get(i);
		        String valor = extrato.get(i + 1);
		        String data = extrato.get(i + 2);

		        // Adiciona os dados à tabela
		        tablepesque.addRow(new Object[]{tipo, data, valor});
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}

	}
}

package br.com.view;

import javax.swing.*;

public class Janela extends JFrame {
	private static final long serialVersionUID = 1L;

	public void janela(String title) {
		setTitle(title);
		setSize(700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(() -> {
//			Janela frame = new Janela();
//			frame.janela("Banco Malvader");
//		});
//	}
}

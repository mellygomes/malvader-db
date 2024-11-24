//package testes;
//
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import javax.swing.text.MaskFormatter;
//import javax.swing.JFormattedTextField;
//import javax.swing.JLabel;
//import javax.swing.JButton;
//
//public class testeFormat extends JFrame {
//
//	private static final long serialVersionUID = 1L;
//	private JPanel contentPane;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					testeFormat frame = new testeFormat();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public testeFormat() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
//		
//		MaskFormatter mascaraCpf = null;
//		
//		try {
//			mascaraCpf = new MaskFormatter("###.###.###-##");
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		
//		JFormattedTextField jFteste = new JFormattedTextField(mascaraCpf);
//		jFteste.setBounds(172, 87, 118, 19);
//		contentPane.add(jFteste);
//		
//		JLabel jLteste = new JLabel("New label");
//		jLteste.setBounds(142, 199, 170, 13);
//		contentPane.add(jLteste);
//		
//		JButton jBteste = new JButton("New button");
//		jBteste.setBounds(160, 154, 85, 21);
//		contentPane.add(jBteste);
//		
//		jBteste.addActionListener(e -> {
//			String formattedValue = jFteste.getText(); // Texto com a máscara
//	        String plainValue = formattedValue.replaceAll("\\D", ""); // Remove tudo que não é número
//			jLteste.setText("Aqui: " + plainValue);
//		});
//		
//	}
//
//}

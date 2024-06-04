package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import telas.Usuarios;
import java.awt.Rectangle;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField NomeCompleto;
	private JTextField txtAlterarUsername;
	private JTextField txtAlterarSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setTitle("Rede Social");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 800, 600);
		contentPane.add(tabbedPane);
		
		JPanel VerFotos = new JPanel();
		tabbedPane.addTab("Ver Fotos", null, VerFotos, null);
		
		JPanel Usuarios = new JPanel();
		tabbedPane.addTab("Usuarios", null, Usuarios, null);
		
		JPanel Perfil = new JPanel();
		tabbedPane.addTab("Perfil", null, Perfil, null);
		Perfil.setLayout(null);
		
		JButton btnAddPostagem = new JButton("Adicionar Postagem");
		btnAddPostagem.setBounds(264, 297, 216, 25);
		Perfil.add(btnAddPostagem);
		
		NomeCompleto = new JTextField();
		NomeCompleto.setEditable(false);
		NomeCompleto.setText("NomeCompleto");
		NomeCompleto.setBounds(197, 68, 327, 33);
		Perfil.add(NomeCompleto);
		NomeCompleto.setColumns(10);
		
		txtAlterarUsername = new JTextField();
		txtAlterarUsername.setColumns(10);
		txtAlterarUsername.setBounds(197, 138, 327, 33);
		Perfil.add(txtAlterarUsername);
		
		txtAlterarSenha = new JTextField();
		txtAlterarSenha.setToolTipText("dasd");
		txtAlterarSenha.setColumns(10);
		txtAlterarSenha.setBounds(197, 215, 327, 33);
		Perfil.add(txtAlterarSenha);
		
		JLabel lblNomeCompleto = new JLabel("Nome Completo");
		lblNomeCompleto.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNomeCompleto.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNomeCompleto.setBounds(-25, 76, 182, 15);
		Perfil.add(lblNomeCompleto);
		
		JLabel lblUserName = new JLabel("Username");
		lblUserName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUserName.setFont(new Font("Dialog", Font.BOLD, 16));
		lblUserName.setBounds(-25, 146, 182, 15);
		Perfil.add(lblUserName);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSenha.setFont(new Font("Dialog", Font.BOLD, 16));
		lblSenha.setBounds(-25, 223, 182, 15);
		Perfil.add(lblSenha);
		
		JButton btnConfirmName = new JButton("✓");
		btnConfirmName.setBounds(562, 68, 44, 33);
		Perfil.add(btnConfirmName);
		
		JButton btnConfirmUserName = new JButton("✓");
		btnConfirmUserName.setBounds(562, 138, 44, 33);
		Perfil.add(btnConfirmUserName);
		
		JButton btnConfirmSenha = new JButton("✓");
		btnConfirmSenha.setBounds(562, 215, 44, 33);
		Perfil.add(btnConfirmSenha);
		
		JLabel lblNomeCompletoPage = new JLabel("NomeCompleto");
		lblNomeCompletoPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeCompletoPage.setFont(new Font("Dialog", Font.BOLD, 30));
		lblNomeCompletoPage.setBounds(264, 12, 273, 33);
		Perfil.add(lblNomeCompletoPage);
	}
}

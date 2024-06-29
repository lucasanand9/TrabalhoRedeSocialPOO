package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import negocios.RedeSocial;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField NomeCompleto;
	private JPasswordField Senha;
	private JTextField Username;


	public Cadastro(RedeSocial rede) {
		setTitle("Cadastro");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 105, 776, 31);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 498, 776, 15);
		contentPane.add(separator_1);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 23));
		lblPassword.setBounds(311, 298, 165, 44);
		contentPane.add(lblPassword);
		
		JLabel lblUsername = new JLabel("Nome Completo");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Dialog", Font.BOLD, 23));
		lblUsername.setBounds(289, 105, 227, 44);
		contentPane.add(lblUsername);
		
		NomeCompleto = new JTextField();
		NomeCompleto.setColumns(10);
		NomeCompleto.setBounds(156, 150, 485, 31);
		contentPane.add(NomeCompleto);
		
		Senha = new JPasswordField();
		Senha.setBounds(156, 335, 485, 34);
		contentPane.add(Senha);
		
		JLabel lblRegistrar = new JLabel("REGISTRAR");
		lblRegistrar.setFont(new Font("Dialog", Font.BOLD, 69));
		lblRegistrar.setBounds(181, 12, 439, 88);
		contentPane.add(lblRegistrar);
		
		JButton btnCadastro = new JButton("CADASTRAR");
		btnCadastro.addActionListener(new ActionListener() {
			long id = 0;
			public void actionPerformed(ActionEvent e) {
				if(rede.cadastra(id, Username.getText(), new String(Senha.getPassword()), NomeCompleto.getText())) {
					Login log = new Login(rede);
	                log.setVisible(true);
	                dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Ja existe uma conta com esse Username");
				}
			}
		});
		btnCadastro.setFont(new Font("Dialog", Font.BOLD, 20));
		btnCadastro.setBounds(156, 420, 485, 50);
		contentPane.add(btnCadastro);
		
		JButton LogarSwitch = new JButton("Logar");
		LogarSwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login log = new Login(rede);
                log.setVisible(true);
                dispose();
			}
		});
		LogarSwitch.setBounds(406, 518, 117, 25);
		contentPane.add(LogarSwitch);
		
		JLabel lblNewLabel_1 = new JLabel("Ja tem conta?");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_1.setBounds(276, 523, 150, 15);
		contentPane.add(lblNewLabel_1);
		
		Username = new JTextField();
		Username.setColumns(10);
		Username.setBounds(156, 243, 485, 31);
		contentPane.add(Username);
		
		JLabel lblUsername_1 = new JLabel("Username");
		lblUsername_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername_1.setFont(new Font("Dialog", Font.BOLD, 23));
		lblUsername_1.setBounds(311, 204, 165, 44);
		contentPane.add(lblUsername_1);
	}

}

package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exception.SelectException;
import negocios.RedeSocial;

import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Username;
	private JPasswordField Senha;
	/**
	 * Create the frame.
	 */
	public Login(RedeSocial rede) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setTitle("Login");
		setResizable(false);
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
		lblPassword.setBounds(299, 229, 165, 44);
		contentPane.add(lblPassword);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Dialog", Font.BOLD, 23));
		lblUsername.setBounds(299, 148, 165, 44);
		contentPane.add(lblUsername);
		
		Username = new JTextField();
		Username.setColumns(10);
		Username.setBounds(154, 186, 485, 31);
		contentPane.add(Username);
		
		Senha = new JPasswordField();
		Senha.setBounds(154, 266, 485, 34);
		contentPane.add(Senha);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 69));
		lblNewLabel.setBounds(268, 12, 255, 88);
		contentPane.add(lblNewLabel);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rede.login(Username.getText(), new String(Senha.getPassword()))) {
						Principal princ = new Principal(rede);
						princ.setVisible(true);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Conta nao encontrada");
					}
				} catch (HeadlessException | SelectException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnLogin.setFont(new Font("Dialog", Font.BOLD, 20));
		btnLogin.setBounds(154, 351, 485, 50);
		contentPane.add(btnLogin);
		
		JButton CadastrarSwitch = new JButton("Cadastrar");
		CadastrarSwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cadastro cad = new Cadastro(rede);
                cad.setVisible(true);
                dispose();
			}
		});
		CadastrarSwitch.setBorder(null);
		CadastrarSwitch.setBounds(406, 518, 117, 25);
		contentPane.add(CadastrarSwitch);
		
		JLabel lblNewLabel_1 = new JLabel("Criar conta");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_1.setBounds(304, 523, 92, 15);
		contentPane.add(lblNewLabel_1);
	}
}

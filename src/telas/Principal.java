package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dados.Usuario;
import negocios.RedeSocial;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtAlterarNomeCompleto;
	private JTextField txtAlterarUsername;
	private JTextField txtAlterarSenha;
	private JTextField txtUsuariosDaPlataforma;
	private JTextField AddAmigo;
	private JTable table;
	private JTextField txtAlterarBio;

	public Principal(RedeSocial rede) {
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
		VerFotos.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(3, 58, 791, 513);
		VerFotos.add(scrollPane_1);
		
		table = new JTable(new ImgTable(rede));
		table.setRowHeight(120);
		scrollPane_1.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Postagens");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel_1.setBounds(289, 12, 217, 31);
		VerFotos.add(lblNewLabel_1);
		
		JPanel Usuarios = new JPanel();
		tabbedPane.addTab("Usuarios", null, Usuarios, null);
		Usuarios.setLayout(null);
		
		txtUsuariosDaPlataforma = new JTextField();
		txtUsuariosDaPlataforma.setBorder(null);
		txtUsuariosDaPlataforma.setInheritsPopupMenu(true);
		txtUsuariosDaPlataforma.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtUsuariosDaPlataforma.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuariosDaPlataforma.setEditable(false);
		txtUsuariosDaPlataforma.setText("Usuarios Da Plataforma");
		txtUsuariosDaPlataforma.setBounds(163, 12, 463, 43);
		Usuarios.add(txtUsuariosDaPlataforma);
		txtUsuariosDaPlataforma.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 67, 771, 2);
		Usuarios.add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 80, 771, 317);
		Usuarios.add(scrollPane);
		
		JList list = new JList();
		list.setFont(new Font("Dialog", Font.BOLD, 16));
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return rede.getUsuariosTotais().size();
			}
			public Object getElementAt(int index) {
				return rede.getUsuariosTotais().get(index).getUsername();
			}
		});
		scrollPane.setViewportView(list);
		
		AddAmigo = new JTextField();
		AddAmigo.setBounds(76, 445, 449, 43);
		Usuarios.add(AddAmigo);
		AddAmigo.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Adicionar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!rede.addAmigo(AddAmigo.getText())) {
					JOptionPane.showMessageDialog(null, "Conta nao encontrada");
				}else {
					JOptionPane.showMessageDialog(null, "Amigo adicionado");
					Principal principal = new Principal(rede);
					principal.setVisible(true);
					dispose();
				}
			}
		});
		btnNewButton_1.setBounds(534, 445, 176, 43);
		Usuarios.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Adicionar Amigo");
		lblNewLabel.setBounds(76, 427, 142, 15);
		Usuarios.add(lblNewLabel);

		
		JPanel Perfil = new JPanel();
		tabbedPane.addTab("Perfil", null, Perfil, null);
		Perfil.setLayout(null);
		
		JButton btnAddPostagem = new JButton("Adicionar Postagem");
		btnAddPostagem.setBounds(252, 321, 319, 33);
		Perfil.add(btnAddPostagem);
		
		txtAlterarNomeCompleto = new JTextField();
		txtAlterarNomeCompleto.setBounds(197, 68, 422, 33);
		Perfil.add(txtAlterarNomeCompleto);
		txtAlterarNomeCompleto.setColumns(10);
		
		txtAlterarUsername = new JTextField();
		txtAlterarUsername.setColumns(10);
		txtAlterarUsername.setBounds(197, 138, 422, 33);
		Perfil.add(txtAlterarUsername);
		
		txtAlterarSenha = new JTextField();
		txtAlterarSenha.setToolTipText("dasd");
		txtAlterarSenha.setColumns(10);
		txtAlterarSenha.setBounds(197, 215, 422, 33);
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
		btnConfirmName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtAlterarNomeCompleto.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Coloque um nome valido");
				}else {
					rede.editarNomeCompleto(txtAlterarNomeCompleto.getText());	
				}
				
			}
		});
		btnConfirmName.setBounds(658, 68, 44, 33);
		Perfil.add(btnConfirmName);
		
		JButton btnConfirmUserName = new JButton("✓");
		btnConfirmUserName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtAlterarUsername.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Coloque um nome valido");
				}else {
					if(rede.editarUserName(txtAlterarUsername.getText())) {
						Principal principal = new Principal(rede);
		                principal.setVisible(true);
		                dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Ja existe uma conta com esse Username");
					}
				}
				
			}
		});
		btnConfirmUserName.setBounds(658, 138, 44, 33);
		Perfil.add(btnConfirmUserName);
		
		JButton btnConfirmSenha = new JButton("✓");
		btnConfirmSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtAlterarSenha.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Coloque uma senha valida");
				}else {
					rede.editarSenha(txtAlterarSenha.getText());
					Login log = new Login(rede);
		            log.setVisible(true);
		            dispose();
	            }
			}
		});
		btnConfirmSenha.setBounds(658, 215, 44, 33);
		Perfil.add(btnConfirmSenha);
		
		JLabel lblNomeCompletoPage = new JLabel("<html> <h1>" + rede.getNomeLogado() + "</h1> </html>");
		lblNomeCompletoPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeCompletoPage.setFont(new Font("Dialog", Font.BOLD, 30));
		lblNomeCompletoPage.setBounds(264, 12, 273, 33);
		Perfil.add(lblNomeCompletoPage);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rede.logout();
				Login log = new Login(rede);
                log.setVisible(true);
                dispose();
			}
		});
		btnNewButton.setBounds(633, 521, 150, 40);
		Perfil.add(btnNewButton);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(26, 411, 757, 98);
		Perfil.add(scrollPane_2);
		
		JList list_1 = new JList();
		list_1.setModel(new AbstractListModel() {
			public int getSize() {
				return rede.verSeguidores().size();
			}
			public Object getElementAt(int index) {
				return rede.verSeguidores().get(index).getUsername();
			}
		});
		scrollPane_2.setViewportView(list_1);
		
		JLabel lblBio = new JLabel(rede.getBioLogado());
		lblBio.setHorizontalAlignment(SwingConstants.CENTER);
		lblBio.setBounds(197, 366, 422, 33);
		Perfil.add(lblBio);
		
		JLabel lblBiografia = new JLabel("Biografia");
		lblBiografia.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBiografia.setFont(new Font("Dialog", Font.BOLD, 16));
		lblBiografia.setBounds(12, 289, 145, 15);
		Perfil.add(lblBiografia);
		
		txtAlterarBio = new JTextField();
		txtAlterarBio.setBounds(197, 280, 422, 33);
		Perfil.add(txtAlterarBio);
		txtAlterarBio.setColumns(10);
		
		JButton btnConfirmSenha_1 = new JButton("✓");
		btnConfirmSenha_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rede.editarBio(txtAlterarBio.getText());
				Principal principal = new Principal(rede);
	            principal.setVisible(true);
	            dispose();
			}
		});
		btnConfirmSenha_1.setBounds(658, 280, 44, 33);
		Perfil.add(btnConfirmSenha_1);
		
		JButton btnNewButton_2 = new JButton("Remover");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rede.removeAmigo((String)list_1.getSelectedValue())) {
					Principal principal = new Principal(rede);
	                principal.setVisible(true);
	                dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Erro ao remover amigo");
				}
			}
		});
		btnNewButton_2.setBounds(26, 529, 117, 25);
		Perfil.add(btnNewButton_2);
	}
}

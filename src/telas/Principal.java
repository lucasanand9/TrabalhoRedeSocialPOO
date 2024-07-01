package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dados.Postagem;
import dados.Usuario;
import exception.DeleteException;
import exception.InsertException;
import exception.SelectException;
import exception.UpdateException;
import negocios.RedeSocial;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtAlterarNomeCompleto;
	private JTextField txtAlterarUsername;
	private JTextField txtAlterarSenha;
	private JTable table;
	private JTextField txtAlterarBio;

	
	private Image img = null;
	private JFileChooser imageGetter = new JFileChooser();
	
	
	private JLabel lblNomeCompletoPage = new JLabel();
	private JList listaAmigos = new JList();
	JScrollPane scrollPane_2 = new JScrollPane();
	
	public Principal(RedeSocial rede) {
		setTitle("Rede Social");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
				try {
					return rede.getUsuariosTotais().size();
				} catch (SelectException e) {
					e.printStackTrace();
				}
				return 0;
			}
			public Object getElementAt(int index) {
				try {
					return rede.getUsuariosTotais().get(index).getUsername();
				} catch (SelectException e) {
					e.printStackTrace();
				}
				return null;
			}
		});
		scrollPane.setViewportView(list);
		
		JButton btnNewButton_1 = new JButton("Adicionar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!rede.addAmigo((String)list.getSelectedValue())) {
						JOptionPane.showMessageDialog(null, "Conta nao encontrada ou ja tem esse usuario adicionado");
					}else {
						JOptionPane.showMessageDialog(null, "Amigo adicionado");
						table = new JTable(new ImgTable(rede));
						table.setRowHeight(120);
						scrollPane_1.setViewportView(table);
						scrollPane_2.setColumnHeaderView(listaAmigos);
					}
				} catch (HeadlessException | InsertException | SelectException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(106, 444, 598, 43);
		Usuarios.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Usuarios da plataforma");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 22));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(173, 9, 467, 46);
		Usuarios.add(lblNewLabel_2);

		
		JPanel Perfil = new JPanel();
		tabbedPane.addTab("Perfil", null, Perfil, null);
		Perfil.setLayout(null);
		
		
		JButton btnAddPostagem = new JButton("Adicionar Postagem");
		btnAddPostagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddImage addImage = new AddImage(rede);
				addImage.setVisible(true);
			}
		});
		btnAddPostagem.setBounds(253, 296, 319, 33);
		Perfil.add(btnAddPostagem);
		
		txtAlterarNomeCompleto = new JTextField();
		txtAlterarNomeCompleto.setText(rede.getLogado().getNomeCompleto());
		txtAlterarNomeCompleto.setBounds(200, 46, 422, 33);
		Perfil.add(txtAlterarNomeCompleto);
		txtAlterarNomeCompleto.setColumns(10);
		
		txtAlterarUsername = new JTextField();
		txtAlterarUsername.setText(rede.getLogado().getUsername());
		txtAlterarUsername.setColumns(10);
		txtAlterarUsername.setBounds(200, 116, 422, 33);
		Perfil.add(txtAlterarUsername);
		
		txtAlterarSenha = new JTextField();
		txtAlterarSenha.setText(rede.getLogado().getPassword());
		txtAlterarSenha.setToolTipText("dasd");
		txtAlterarSenha.setColumns(10);
		txtAlterarSenha.setBounds(200, 176, 422, 33);
		Perfil.add(txtAlterarSenha);
		
		JLabel lblNomeCompleto = new JLabel("Nome Completo");
		lblNomeCompleto.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNomeCompleto.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNomeCompleto.setBounds(-22, 54, 182, 15);
		Perfil.add(lblNomeCompleto);
		
		JLabel lblUserName = new JLabel("Username");
		lblUserName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUserName.setFont(new Font("Dialog", Font.BOLD, 16));
		lblUserName.setBounds(-22, 124, 182, 15);
		Perfil.add(lblUserName);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSenha.setFont(new Font("Dialog", Font.BOLD, 16));
		lblSenha.setBounds(-22, 184, 182, 15);
		Perfil.add(lblSenha);
		
		
		if(rede.getLogado().getNomeCompleto().equals("")) {
			lblNomeCompletoPage.setText("<html> <h1>" + rede.getNomeLogado() + "</h1> </html>");
		}else {
			lblNomeCompletoPage.setText("<html> <h1>" + rede.getLogado().getNomeCompleto() + "</h1> </html>");
		}
		
		JButton btnConfirmName = new JButton("✓");
		btnConfirmName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtAlterarNomeCompleto.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Coloque um nome valido");
				}else {
					try {
						rede.editarNomeCompleto(txtAlterarNomeCompleto.getText());
					} catch (UpdateException e1) {
						e1.printStackTrace();
					}	
					lblNomeCompletoPage.setText("<html> <h1>" + rede.getLogado().getNomeCompleto() + "</h1> </html>");
					JOptionPane.showMessageDialog(null, "Nome alterado!");
				}
				
			}
		});
		btnConfirmName.setBounds(661, 46, 44, 33);
		Perfil.add(btnConfirmName);
		
		JButton btnConfirmUserName = new JButton("✓");
		btnConfirmUserName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtAlterarUsername.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Coloque um nome valido");
				}else {
					try {
						if((rede.editarUserName(txtAlterarUsername.getText()))) {
							JOptionPane.showMessageDialog(null, "Username alterado!");
						}else {
							JOptionPane.showMessageDialog(null, "Ja existe uma conta com esse Username");
						}
					} catch (HeadlessException | UpdateException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnConfirmUserName.setBounds(661, 116, 44, 33);
		Perfil.add(btnConfirmUserName);
		
		JButton btnConfirmSenha = new JButton("✓");
		btnConfirmSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtAlterarSenha.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Coloque uma senha valida");
				}else {
					try {
						rede.editarSenha(txtAlterarSenha.getText());
					} catch (UpdateException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Senha alterada, volte para pagina de login");
					rede.logout();
					Login log = new Login(rede);
		            log.setVisible(true);
		            dispose();
	            }
			}
		});
		btnConfirmSenha.setBounds(661, 176, 44, 33);
		Perfil.add(btnConfirmSenha);
		
		if(rede.getLogado().getNomeCompleto().equals("")) {
			lblNomeCompletoPage = new JLabel("<html> <h1>" + rede.getNomeLogado() + "</h1> </html>");
		}else {
			lblNomeCompletoPage = new JLabel("<html> <h1>" + rede.getLogado().getNomeCompleto() + "</h1> </html>");
		}
		lblNomeCompletoPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeCompletoPage.setFont(new Font("Dialog", Font.BOLD, 30));
		lblNomeCompletoPage.setBounds(265, 1, 273, 33);
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
		btnNewButton.setBounds(699, 491, 84, 40);
		Perfil.add(btnNewButton);
		
		
		scrollPane_2.setBounds(26, 389, 757, 98);
		Perfil.add(scrollPane_2);
		
		
		listaAmigos.setModel(new AbstractListModel() {
			public int getSize() {
				return rede.verSeguidores().size();
			}
			public Object getElementAt(int index) {
				return rede.verSeguidores().get(index).getUsername();
			}
		});
		scrollPane_2.setColumnHeaderView(listaAmigos);
		
		JLabel lblBio = new JLabel(rede.getBioLogado());
		lblBio.setHorizontalAlignment(SwingConstants.CENTER);
		lblBio.setBounds(200, 341, 422, 33);
		Perfil.add(lblBio);
		
		JLabel lblBiografia = new JLabel("Biografia");
		lblBiografia.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBiografia.setFont(new Font("Dialog", Font.BOLD, 16));
		lblBiografia.setBounds(15, 250, 145, 15);
		Perfil.add(lblBiografia);
		
		txtAlterarBio = new JTextField();
		txtAlterarBio.setText(rede.getBioLogado());
		txtAlterarBio.setBounds(200, 241, 422, 33);
		Perfil.add(txtAlterarBio);
		txtAlterarBio.setColumns(10);
		
		JButton Biografia = new JButton("✓");
		Biografia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rede.editarBio(txtAlterarBio.getText());
				} catch (UpdateException e1) {
					e1.printStackTrace();
				}
				lblBio.setText(rede.getBioLogado());
				JOptionPane.showMessageDialog(null, "Biografia alterada");
			}
		});
		Biografia.setBounds(661, 241, 44, 33);
		Perfil.add(Biografia);
		
		JButton btnNewButton_2 = new JButton("Remover");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rede.removeAmigo((String)listaAmigos.getSelectedValue())) {
						table = new JTable(new ImgTable(rede));
						table.setRowHeight(120);
						scrollPane_1.setViewportView(table);
						scrollPane_2.setColumnHeaderView(listaAmigos);
					}else {
						JOptionPane.showMessageDialog(null, "Erro ao remover amigo");
					}
				} catch (HeadlessException | DeleteException | SelectException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(36, 499, 117, 25);
		Perfil.add(btnNewButton_2);
	}
}

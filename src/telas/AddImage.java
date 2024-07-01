package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dados.Postagem;
import negocios.RedeSocial;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class AddImage extends JFrame {
	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFileChooser imageGetter = new JFileChooser();
	private Image img = null;
	private JTextField Legenda;

	public AddImage(RedeSocial rede) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Adicione uma imagem");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 12, 440, 36);
		contentPane.add(lblNewLabel);
		
		JButton btnAddPostagem = new JButton("Selecionar");
		btnAddPostagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int state = imageGetter.showOpenDialog(btnAddPostagem);
				 if(state!= JFileChooser.APPROVE_OPTION)
	                    return;
				 try {
					 File f = imageGetter.getSelectedFile();
	                 img = ImageIO.read(f);
				 }catch(IOException exception){
					 System.out.println("erro");
				 }
				 
				 if(img==null){
					 JOptionPane.showMessageDialog(null, "Escolha uma imagem");
	                    return;
	             }
				
			}
		});
		btnAddPostagem.setBounds(100, 65, 256, 25);
		contentPane.add(btnAddPostagem);
		
		JButton Postar = new JButton("Postar");
		Postar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((img == null) || (Legenda.getText().equals(""))) {
					JOptionPane.showMessageDialog(null, "Algo deu errado, adicione novamente uma imagem e uma legenda");
				}else {
				 Postagem post = rede.criaPostagem(img, Legenda.getText());
				 rede.fazPostagem(post);
				 img = null;
				 System.out.println(rede.mostrarMinhasPostagens());
				 dispose();
			}
			 img = null;
		  }
		});
		Postar.setBounds(163, 228, 117, 25);
		contentPane.add(Postar);
		
		JLabel lblNewLabel_1 = new JLabel("Adicione uma legenda");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 22));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(12, 124, 428, 25);
		contentPane.add(lblNewLabel_1);
		
		Legenda = new JTextField();
		Legenda.setBounds(100, 173, 256, 25);
		contentPane.add(Legenda);
		Legenda.setColumns(10);
	}
}

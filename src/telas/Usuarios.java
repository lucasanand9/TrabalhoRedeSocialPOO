package telas;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import negocios.RedeSocial;

import javax.swing.SpringLayout;
import javax.swing.JTextField;

public class Usuarios extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNomecompleto;

	/**
	 * Create the panel.
	 */
	public Usuarios(RedeSocial rede) {
		setBackground(new Color(192, 191, 188));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblUserName = new JLabel("UserName");
		springLayout.putConstraint(SpringLayout.NORTH, lblUserName, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblUserName, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblUserName, -8, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblUserName, -672, SpringLayout.EAST, this);
		lblUserName.setFont(new Font("Dialog", Font.BOLD, 20));
		add(lblUserName);
		
		JSeparator separator = new JSeparator();
		springLayout.putConstraint(SpringLayout.NORTH, separator, 0, SpringLayout.NORTH, lblUserName);
		springLayout.putConstraint(SpringLayout.WEST, separator, 19, SpringLayout.EAST, lblUserName);
		springLayout.putConstraint(SpringLayout.SOUTH, separator, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, separator, -639, SpringLayout.EAST, this);
		separator.setOrientation(SwingConstants.VERTICAL);
		add(separator);
		
		txtNomecompleto = new JTextField();
		lblUserName.setLabelFor(txtNomecompleto);
		txtNomecompleto.setBorder(null);
		txtNomecompleto.setBackground(new Color(192, 191, 188));
		txtNomecompleto.setHorizontalAlignment(SwingConstants.CENTER);
		txtNomecompleto.setText("NomeCompleto");
		txtNomecompleto.setEditable(false);
		springLayout.putConstraint(SpringLayout.NORTH, txtNomecompleto, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, txtNomecompleto, 6, SpringLayout.EAST, separator);
		springLayout.putConstraint(SpringLayout.SOUTH, txtNomecompleto, 32, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, txtNomecompleto, 629, SpringLayout.EAST, separator);
		add(txtNomecompleto);
		txtNomecompleto.setColumns(10);

	}
}

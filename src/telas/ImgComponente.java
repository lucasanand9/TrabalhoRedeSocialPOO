package telas;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import dados.Postagem;

public class ImgComponente {
	   	JPanel postPanel = new JPanel();
	    ImageIcon mainFoto = new ImageIcon();

	    public void PostComponent(Postagem p){
	        mainFoto.setImage(p.getFoto());
	    }
}

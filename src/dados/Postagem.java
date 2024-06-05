package dados;

import java.awt.Image;

public class Postagem {
	private long id;
	private Image foto;
	
	public long getId() {
		return id;
	}
	public Image getFoto() {
		return foto;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setFoto(Image foto) {
		this.foto = foto;
	}
	@Override
	public String toString() {
		return "id = " + id + " [foto = " + foto + "]";
	}
	
	
}

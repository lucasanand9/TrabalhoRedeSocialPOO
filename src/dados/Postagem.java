package dados;

import java.awt.Image;
import java.io.File;

public class Postagem {
	private long id;
	private long idUsuario;
	private Image foto;
	private String legenda;
	private File file;
	
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
	public String getLegenda() {
		return legenda;
	}
	public void setLegenda(String legenda) {
		this.legenda = legenda;
	}
	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "id = " + id + " [foto = " + foto + ", " + legenda + "]";
	}
	

	
	
}

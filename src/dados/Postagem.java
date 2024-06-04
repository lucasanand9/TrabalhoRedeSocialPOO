package dados;

public class Postagem {
	private long id;
	private String foto;
	public long getId() {
		return id;
	}
	public String getFoto() {
		return foto;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	@Override
	public String toString() {
		return "id = " + id + " [foto = " + foto + "]";
	}
	
	
}

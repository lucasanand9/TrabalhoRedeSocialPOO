package dados;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private long id;
	private String username;
	private String password;
	private String nomeCompleto;
	private String biografia;
	private List<Usuario> amigos = new ArrayList<Usuario>();
	private List<Postagem> postagens = new ArrayList<Postagem>();
	
	//Metodos para a lista de amigos e as postagem feitas pelo usuario
	
	//Adiciona uma postagem na lista de postagem do usuario
	public void realizarPostagem(Postagem post) {
		this.postagens.add(post);
	}
	
	//Remove uma postagem da lista de postagens do usuario
	public boolean removePost(Postagem post) {
		return postagens.remove(post);
	}
	
	//Adiciona um novo amigo na lista de amigos do usuario
	public void addAmigo(Usuario user) {
		this.amigos.add(user);
	}
	
	//Remove um amigo da lista de amigos do usuarip
	public boolean removeAmigo(String nome) {
		Usuario temp = new Usuario();
		for(Usuario u : amigos) {
			if(u.getUsername().equals(nome)) {
				temp = u;
			}
		}
		return amigos.remove(temp);
	}
	
	
	
	//getters e setters
	
	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	
	public List<Usuario> getAmigos(){
		return this.amigos;
	}
	
	public List<Postagem> getPostagens(){
		return this.postagens;
	}

	
	//toString
	@Override
	public String toString() {
		return "id = " + id + " [username=" + username + "\nnomeCompleto="
				+ nomeCompleto + ", biografia=" + biografia +  "\npostagens=" + postagens + "\namigos=" + amigos + "]";
	}
	
	
}

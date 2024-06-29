package negocios;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import dados.Postagem;
import dados.Usuario;

public class RedeSocial {
	private List<Usuario> usuarioRedeSocial = new ArrayList<Usuario>();
	private Usuario logadoAtual = null;
	
	
	public String getNomeLogado() {
		return this.logadoAtual.getUsername();
	}
	
	public String getBioLogado() {
		return this.logadoAtual.getBiografia();
	}
	public Usuario getLogado() {
		return logadoAtual;
	}
	

	//Realiza o login de um usurio, verificando o username e senha, depois da verificação troca a variavel de logadoAtual para o usuario q tem os dados correspondentes
	public boolean login(String nome, String senha) {
		for(Usuario u : usuarioRedeSocial) {
			//verifica se tem alg usuario q tem o mesmo nome e a senha que o usuario passou
			if(u.getUsername().equals(nome) && u.getPassword().equals(senha)) {
				logadoAtual = u;
				System.out.println("Sr." + u.getUsername() + " está logado");
				return true;
			}
		}
		return false;
	}
	
	//Trasnforma o logadoAtual para nulo
	public void logout() {
		logadoAtual = null;
	}
	
	//Cria um novo usuario e adiciona na lista de usuarios
	public boolean cadastra(long id , String nome, String password, String nomeCompleto) {
		for(Usuario u:usuarioRedeSocial) {
			if(u.getUsername().equals(nome)) {
				return false;
			}
		}
		Usuario temp = new Usuario();
		temp.setId(id);
		temp.setPassword(password);
		temp.setUsername(nome);
		temp.setNomeCompleto(nomeCompleto);
		usuarioRedeSocial.add(temp);
		return true;
	}
	
	//Mostra todas as postagens dos usuarios que o logadoAtual segue
	public List<Postagem> mostrarPostagem(){
		if(logadoAtual == null) {
			System.err.println("Nenhum usuario logado");
			return null;
		}
		List<Postagem> postagens = new ArrayList<Postagem>();
		for(Usuario u : logadoAtual.getAmigos()) {
			postagens.addAll(u.getPostagens());
		}
		return postagens;
	}
	
	public List<Postagem> mostrarMinhasPostagens(){
		if(logadoAtual == null) {
			System.err.println("Nenhum usuario logado");
			return null;
		}
		return logadoAtual.getPostagens();
	}
	
	//Adiciona um novo usuario na lista de amigos do logadoAtual
	public boolean addAmigo(String nome) {
		System.out.println(nome);
		if(logadoAtual == null) {
			System.err.println("Nenhum usuario logado");
			return false;
		}
		for(Usuario u : usuarioRedeSocial) {
			if(u.getUsername().equals(nome) && !nome.equals(this.logadoAtual.getUsername())) {
				for(Usuario p : logadoAtual.getAmigos()) {
					if(p.getUsername().equals(nome)) {
						return false;
					}
				}
				logadoAtual.addAmigo(u);
				return true;
			}
		}
		return false;
	}
	
	//Remove um usuario da lista de amigos do logadoAtual
	public boolean removeAmigo(String nome) {
		if(logadoAtual == null) {
			System.err.println("Nenhum usuario logado");
			return false;
		}
		return logadoAtual.removeAmigo(nome);
	}
	
	//Adiciona uma Postagem na lista de postagem do logadoAtual
	public boolean fazPostagem(Postagem post) {
		if(logadoAtual == null) {
			System.err.println("Nenhum usuario logado");
			return false;
		}
		logadoAtual.realizarPostagem(post);
		return true;
	}
	
	//Cria uma nova postagem
	public Postagem criaPostagem(Image foto, String legenda ){
		Postagem post = new Postagem();
		post.setFoto(foto);
		post.setLegenda(legenda);
		return post;
	}
	
	//Procura um usuario na lista de usuarios da rede social
	public Usuario procuraPessoa(String nome) {
		for(Usuario u : usuarioRedeSocial) {
			if(u.getUsername().equals(nome)) {
				return u;
			}
		}
		System.err.println("Não exite esse Usuario");
		return null;
	}
	
	//Retorna uma lista com os amigos do logadoAtual
	public List<Usuario> verSeguidores(){
		if(logadoAtual == null) {
			System.err.println("Nenhum usuario logado");
			return null;
		}
		return logadoAtual.getAmigos();	
	}
	
	public void vePerfil() {
		if(logadoAtual == null) {
			System.err.println("Nenhum usuario logado");
			return;
		}
		System.out.println("Nome do perfil: " + logadoAtual.getUsername()+
				"\nBiografia: " + logadoAtual.getBiografia() + 
				"\nAmigos: " + logadoAtual.getAmigos() + 
				"\nPostagens: " + logadoAtual.getPostagens() + "\n");
	}
	
	//Edita as informaçoes do logadoAtual
	public boolean editarSenha(String senha) {
		if(logadoAtual == null) {
			System.err.println("Nenhum usuario logado");
			return false;
		}
		logadoAtual.setPassword(senha);
		return true;
	}
	
	public boolean editarUserName(String nome) {
		if(logadoAtual == null) {
			System.err.println("Nenhum usuario logado");
			return false;
		}
		for(Usuario u:usuarioRedeSocial) {
			if(u.getUsername().equals(nome)) {
				System.err.println("Outro usuario ja possui esse nome");
				return false;
			}
		}
		logadoAtual.setUsername(nome);
		return true;
	}
	
	public boolean editarBio(String bio) {
		if(logadoAtual == null) {
			System.err.println("Nenhum usuario logado");
			return false;
		}
		logadoAtual.setBiografia(bio);
		return true;
	}
	
	public boolean editarNomeCompleto(String nome) {
		if(logadoAtual == null) {
			System.err.println("Nenhum usuario logado");
			return false;
		}
		logadoAtual.setNomeCompleto(nome);
		return true;
	}
	
	public List<Usuario> getUsuariosTotais() {
		return this.usuarioRedeSocial;
	}
	
	
	
}

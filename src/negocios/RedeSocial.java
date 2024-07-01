package negocios;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dados.Postagem;
import dados.Usuario;
import exception.DeleteException;
import exception.InsertException;
import exception.SelectException;
import exception.UpdateException;
import percistencia.Conexao;
import percistencia.PostagemDAO;
import percistencia.UsuarioDAO;

public class RedeSocial {
//	private List<Usuario> usuarioRedeSocial = new ArrayList<Usuario>();
	private UsuarioDAO usuarioDAO;
	private Usuario logadoAtual = null;
	private PostagemDAO postagemDAO;
	
	
	
	public RedeSocial(String senha) throws ClassNotFoundException, SQLException, SelectException {
		Conexao.setSenha("udesc");
		usuarioDAO = UsuarioDAO.getInstance();
		postagemDAO = PostagemDAO.getInstacen();
	}
	
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
	public boolean login(String nome, String senha) throws SelectException {
		logadoAtual = usuarioDAO.login(nome, senha);
		if(logadoAtual == null){
			return false;
		}else {
			return true;
		}
	}

	//Trasnforma o logadoAtual para nulo
	public void logout() {
		logadoAtual = null;
	}
	
	//Cria um novo usuario e adiciona na lista de usuarios
	public boolean cadastra(String nome, String password, String nomeCompleto) throws InsertException {
		Usuario u = new Usuario();
		u.setNomeCompleto(nomeCompleto);
		u.setUsername(nome);
		u.setPassword(password);
		return usuarioDAO.insertUsuario(u);
	}
	
	//Mostra todas as postagens dos usuarios que o logadoAtual segue
	public List<Postagem> mostrarPostagem() throws SelectException, IOException{
		if(logadoAtual == null) {
			System.err.println("Nenhum usuario logado");
			return null;
		}
		List<Postagem> postagens = new ArrayList<Postagem>();
		for(Usuario u : logadoAtual.getAmigos()) {
			postagens.addAll(postagemDAO.selectPostagens(u.getId()));
		}
		return postagens;
	}
	
	public List<Postagem> mostrarMinhasPostagens() throws SelectException, IOException{
		if(logadoAtual == null) {
			System.err.println("Nenhum usuario logado");
			return null;
		}
		return postagemDAO.selectPostagens(logadoAtual.getId());
	}
	
	//Adiciona um novo usuario na lista de amigos do logadoAtual
	public boolean addAmigo(String nome) throws InsertException, SelectException {
		if(logadoAtual == null) {
			System.err.println("Nenhum usuario logado");
			return false;
		}
		if(!logadoAtual.getUsername().equals(nome)) {
			for(Usuario u : logadoAtual.getAmigos()) {
				if(u.getId() == usuarioDAO.selectByUsername(nome).getId()) {
					return false;
				}
			}
			logadoAtual.addAmigo(usuarioDAO.selectByUsername(nome)); 
			System.out.println(logadoAtual.getAmigos());
			return usuarioDAO.adicionaAmigo(logadoAtual, nome);
			
		}
		return false;
	}
	
	//Remove um usuario da lista de amigos do logadoAtual
	public boolean removeAmigo(String nome) throws DeleteException, SelectException {
		if(logadoAtual == null) {
			System.err.println("Nenhum usuario logado");
			return false;
		}
		logadoAtual.removeAmigo(nome);
		return usuarioDAO.removeAmigo(logadoAtual, nome);
	}
	
	//Adiciona uma Postagem na lista de postagem do logadoAtual
	public boolean fazPostagem(Postagem post) throws InsertException {
		if(logadoAtual == null) {
			System.err.println("Nenhum usuario logado");
			return false;
		}
		logadoAtual.realizarPostagem(post);
		postagemDAO.insertPostagem(logadoAtual, post);
		return true;
	}
	
	//Cria uma nova postagem
	public Postagem criaPostagem(Image foto, String legenda, File file ){
		Postagem post = new Postagem();
		post.setFoto(foto);
		post.setLegenda(legenda);
		post.setFile(file);
		return post;
	}
	
	//Procura um usuario na lista de usuarios da rede social
	public Usuario procuraPessoa(String nome) throws SelectException {
		return usuarioDAO.selectByUsername(nome);
	}
	
	//Retorna uma lista com os amigos do logadoAtual
	public List<Usuario> verSeguidores(){
		if(logadoAtual == null) {
			System.err.println("Nenhum usuario logado");
			return null;
		}
		return logadoAtual.getAmigos();	
		//ver depois
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
	public boolean editarSenha(String senha) throws UpdateException {
		if(logadoAtual == null) {
			System.err.println("Nenhum usuario logado");
			return false;
		}
		usuarioDAO.updateSenha(logadoAtual, senha);
		logadoAtual.setPassword(senha);
		return true;
	}
	
	public boolean editarUserName(String nome) throws UpdateException {
		if(logadoAtual == null) {
			System.err.println("Nenhum usuario logado");
			return false;
		}
		Usuario temp = null;
		try {
			temp = usuarioDAO.selectByUsername(nome);
		} catch (SelectException e) {
			e.printStackTrace();
		}
		if(temp == null) {
			if(usuarioDAO.updateUsername(logadoAtual, nome)) {
				logadoAtual.setUsername(nome);
				return true;
			}
		}
		return false;
	}
	
	public boolean editarBio(String bio) throws UpdateException {
		if(logadoAtual == null) {
			System.err.println("Nenhum usuario logado");
			return false;
		}
		usuarioDAO.updateBiografia(logadoAtual, bio);
		logadoAtual.setBiografia(bio);
		return true;
	}
	
	public boolean editarNomeCompleto(String nome) throws UpdateException {
		if(logadoAtual == null) {
			System.err.println("Nenhum usuario logado");
			return false;
		}
		usuarioDAO.updateNomeCompleto(logadoAtual, nome);
		logadoAtual.setNomeCompleto(nome);
		return true;
	}
	
	public List<Usuario> getUsuariosTotais() throws SelectException {
		return usuarioDAO.select();
	}
	
	
	
}

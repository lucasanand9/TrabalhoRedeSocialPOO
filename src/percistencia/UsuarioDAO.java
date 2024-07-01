package percistencia;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import dados.Usuario;
import exception.DeleteException;
import exception.InsertException;
import exception.SelectException;
import exception.UpdateException;

public class UsuarioDAO {
	private static UsuarioDAO instance = null;
	
	private PreparedStatement insertUsuario;
	private PreparedStatement adicionaAmigo;
	private PreparedStatement removeAmigo;
	private PreparedStatement updateNomeCompleto;
	private PreparedStatement updateUsername;
	private PreparedStatement updateSenha;
	private PreparedStatement updateBiografia;
	private PreparedStatement selectByUsername;
	private PreparedStatement selectById;
	private PreparedStatement selectAmigos;
	private PreparedStatement select;
	private PreparedStatement login;
	
	
	public static UsuarioDAO getInstance() throws ClassNotFoundException, SQLException, SelectException{
		if(instance == null) {
			instance = new UsuarioDAO();
		}
		return instance;
	}
	
	public UsuarioDAO() throws ClassNotFoundException, SQLException, SelectException{
		Connection conexao = Conexao.getConexao();
		insertUsuario = conexao.prepareStatement("insert into usuario(nomeCompleto, username, senha, biografia) values (?,?,?,?)");
		adicionaAmigo = conexao.prepareStatement("insert into listaAmigos(id_logado, id_amigo) values (?,?)");
		removeAmigo = conexao.prepareStatement("delete from listaAmigos where id_logado = ? and id_amigo = ?");
		updateNomeCompleto = conexao.prepareStatement("update usuario set nomeCompleto = ? where id = ?");
		updateUsername = conexao.prepareStatement("update usuario set username = ? where id = ?");
		updateSenha = conexao.prepareStatement("update usuario set senha = ? where id = ?");
		updateBiografia = conexao.prepareStatement("update usuario set biografia = ? where id = ?");
		selectByUsername = conexao.prepareStatement("select * from usuario where username = ?");
		selectById = conexao.prepareStatement("select * from usuario where id = ?");
		selectAmigos = conexao.prepareStatement("select * from listaAmigos where id_logado = ?");
		select = conexao.prepareStatement("select * from usuario");
		login = conexao.prepareStatement("select * from usuario where username = ? and senha = ?");
		
	}
	
	public boolean insertUsuario(Usuario u) throws InsertException {
		try {
			insertUsuario.setString(1, u.getNomeCompleto());
			insertUsuario.setString(2, u.getUsername());
			insertUsuario.setString(3, u.getPassword());
			insertUsuario.setString(4, u.getBiografia());
			insertUsuario.executeUpdate();
			return true;
		}catch(SQLException e){
			throw new InsertException("Erro ao inserir usuario");
		}
	}
	
	public boolean adicionaAmigo(Usuario logado, String nomeAmigo) throws InsertException, SelectException {
		try {
			Usuario amigo = selectByUsername(nomeAmigo);
			adicionaAmigo.setLong(1,logado.getId());
			adicionaAmigo.setLong(2,amigo.getId());
			adicionaAmigo.executeUpdate();
			return true;
		}catch(SQLException e) {
			throw new InsertException("Erro ao adicionar amigo");
		}
	}
	
	public boolean removeAmigo(Usuario logado, String nome) throws DeleteException, SelectException{
		try {
			Usuario amigo = selectByUsername(nome);
			removeAmigo.setLong(1,logado.getId());
			removeAmigo.setLong(2,amigo.getId());
			removeAmigo.executeUpdate();
			return true;
		}catch(SQLException e) {
			throw new DeleteException("Erro ao remover amigo");
		}
	}
	
	public void updateNomeCompleto(Usuario logado, String nome) throws UpdateException{
		try {
			updateNomeCompleto.setString(1, nome);
			updateNomeCompleto.setLong(2, logado.getId());
			updateNomeCompleto.executeUpdate();
		}catch(SQLException e) {
			throw new UpdateException("Falha ao fazer o Update");
		}
	}
	
	public boolean updateUsername(Usuario logado, String nome) throws UpdateException{
		try {
			updateUsername.setString(1, nome);
			updateUsername.setLong(2, logado.getId());
			updateUsername.executeUpdate();
			return true;
		}catch(SQLException e) {
			throw new UpdateException("Falha ao fazer o Update");
		}
	}
	
	public boolean updateSenha(Usuario logado, String senha) throws UpdateException{
		try {
			updateSenha.setString(1, senha);
			updateSenha.setLong(2, logado.getId());
			updateSenha.executeUpdate();
			return true;
		}catch(SQLException e) {
			throw new UpdateException("Falha ao fazer o Update");
		}
	}
	
	public boolean updateBiografia(Usuario logado, String bio) throws UpdateException{
		try {
			updateBiografia.setString(1, bio);
			updateBiografia.setLong(2, logado.getId());
			updateBiografia.executeUpdate();
			return true;
		}catch(SQLException e) {
			throw new UpdateException("Falha ao fazer o Update");
		}
	}
	
	public Usuario selectByUsername(String username) throws SelectException{
		try {
			selectByUsername.setString(1, username);
			ResultSet rs = selectByUsername.executeQuery();
			if(rs .next()) {
				Usuario temp = new Usuario();
				temp.setId(rs.getInt(1));
				temp.setNomeCompleto(rs.getString(2));
				temp.setUsername(rs.getString(3));
				temp.setPassword(rs.getString(4));
				temp.setBiografia(rs.getString(5));
				return temp;
			}
		}catch(SQLException e) {
			throw new SelectException("Erro ao buscar Usuario");
		}
		return null;
	}
	
	public Usuario selectById(Long id) throws SelectException{
		try {
			selectById.setLong(1, id);
			ResultSet rs = selectById.executeQuery();
			if(rs .next()) {
				Usuario temp = new Usuario();
				temp.setId(rs.getInt(1));
				temp.setNomeCompleto(rs.getString(2));
				temp.setUsername(rs.getString(3));
				temp.setPassword(rs.getString(4));
				temp.setBiografia(rs.getString(5));
				return temp;
			}
		}catch(SQLException e) {
			throw new SelectException("Erro ao buscar Usuario");
		}
		return null;
	}
	
	
	public List<Usuario> selectAmigos(Usuario logado) throws SelectException{
		List<Usuario> temp = new ArrayList<Usuario>();
		try {
			selectAmigos.setLong(1, logado.getId());
			ResultSet rs = selectAmigos.executeQuery();
			while(rs.next()) {
				Long id = rs.getLong(3);
				temp.add(selectById(id));
			}
		}catch(SQLException e) {
			throw new SelectException("Erro ao buscar amigos");
		}
		return temp;
	}
	
	public List<Usuario> select() throws SelectException{
		List<Usuario> temp = new ArrayList<Usuario>();
		try {
			ResultSet rs = select.executeQuery();
			while(rs.next()) {
				Usuario a = new Usuario();
				a.setId(rs.getInt(1));
				a.setNomeCompleto(rs.getString(2));
				a.setUsername(rs.getString(3));
				a.setPassword(rs.getString(4));
				a.setBiografia(rs.getString(5));
				temp.add(a);
			}
		}catch(SQLException e) {
			throw new SelectException("Erro ao buscar amigos");
		}
		return temp;
	}
	
	public Usuario login(String username, String senha) throws SelectException {
		try {
			login.setString(1, username);
			login.setString(2, senha);
			ResultSet rs = login.executeQuery();
			if(rs.next()) {
				Usuario temp = new Usuario();
				temp.setId(rs.getInt(1));
				temp.setNomeCompleto(rs.getString(2));
				temp.setUsername(rs.getString(3));
				temp.setPassword(rs.getString(4));
				temp.setBiografia(rs.getString(5));
				temp.setAmigos(selectAmigos(temp));
				return temp;
			}
		} catch (SQLException e) {
			throw new SelectException("Nao foi possivel fazer login");
		}
		return null;
	}
	
	
	
	
	
	
}

package percistencia;


import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import dados.Postagem;
import dados.Usuario;
import exception.InsertException;
import exception.SelectException;

public class PostagemDAO {
	private static PostagemDAO instance = null;
	
	public static PostagemDAO getInstacen() throws ClassNotFoundException, SQLException, SelectException{
		if(instance == null) {
			instance = new PostagemDAO();
		}
		return instance;
	}
	
	private PreparedStatement insertPostagem;
	private PreparedStatement selectPostagens;
	
	
	public PostagemDAO() throws ClassNotFoundException, SQLException, SelectException {
		Connection conexao = Conexao.getConexao();
		insertPostagem = conexao.prepareStatement("insert into postagem (legenda, foto, id_usuario) values (?,?,?)");
		selectPostagens = conexao.prepareStatement("select * from postagem where id_usuario = ?");
		
	}
	
	//n sei se funciona
	public boolean insertPostagem(Usuario logado, Postagem post) throws InsertException {//lembrar de passar a img escolhida pelo usuario como file
		try {
			FileInputStream fis = new FileInputStream(post.getFile());
			insertPostagem.setString(1, post.getLegenda());
			insertPostagem.setBinaryStream(2, fis, post.getFile().length());
			insertPostagem.setLong(3, logado.getId());
			insertPostagem.executeUpdate();
			return true;
		} catch (IOException | SQLException e) {
			throw new InsertException("Erro ao salvar a imagem");
		}
	}
	
	public List<Postagem> selectPostagens(Long id) throws SelectException, IOException{
		try {
			List<Postagem> posts = new ArrayList<Postagem>();
			selectPostagens.setLong(1, id);
			ResultSet rs = selectPostagens.executeQuery();
			while(rs.next()) {
				Postagem temp = new Postagem();
				temp.setId(rs.getLong(1));
				temp.setLegenda(rs.getString(2));
				temp.setFoto(ImageIO.read(new ByteArrayInputStream(rs.getBytes(3))));
				temp.setIdUsuario(rs.getLong(4));
				posts.add(temp);
			}
			return posts;
		} catch (SQLException e) {
			throw new SelectException("Erro ao mostrar Imagem");
		}
		
	}
}

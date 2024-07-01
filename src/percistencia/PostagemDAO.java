package percistencia;


import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import dados.Postagem;
import dados.Usuario;
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
		
	}
	
	//n sei se funciona
	public boolean insertPostagem(Usuario logado, Postagem post, File file) {//lembrar de passar a img escolhida pelo usuario como file
		try {
			FileInputStream buff = new FileInputStream(file);
			insertPostagem.setString(1, post.getLegenda());
			insertPostagem.setBinaryStream(2, buff);
			insertPostagem.setLong(3, logado.getId());
			insertPostagem.executeUpdate();
			return true;
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}

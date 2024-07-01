package Apresentacao;

import java.sql.SQLException;

import javax.swing.JFrame;

import exception.DeleteException;
import exception.InsertException;
import exception.SelectException;
import negocios.RedeSocial;
import telas.Login;

public class Sistema {
	private static RedeSocial redeSocial; 
	
	public static void main(String[] args) {
		try {
		redeSocial = new RedeSocial("lucas");

		Login  log = new Login(redeSocial);
		log.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		log.setVisible(true);
		}catch(ClassNotFoundException | SQLException | SelectException  e){
			e.printStackTrace();
		}
		
	}
}
//SCRIPT BANCO DE DADOS	
//
//select * from usuario
//select * from postagem
//select * from listaAmigos
//
//
//create table usuario(
//	id serial,
//	nomeCompleto varchar(50),
//	username varchar(50) unique,
//	senha varchar(50),
//	biografia varchar(50),
//	primary key id
//);
//
//create table postagem(
//	id serial,
//	legenda varchar(50),
//	foto bytea,
//	id_usuario,
//	primary key id,
//	foreign key id_usuario references usuario
//);
//
//create table listaAmigos(
//	id serial,
//	id_logado bigint,
//	id_amigo bigint,
//	primary key id,
//	foreign key from id_logado references usuario,
//	foreign key from id_amigo references usuario
//);

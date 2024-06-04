package Apresentacao;

import negocios.RedeSocial;
import java.lang.Thread;

public class Sistema {
	private static RedeSocial redeSocial = new RedeSocial();
	
	public static void main(String[] args) {
		redeSocial.cadastra(0, "Lucas", "1234", "Lucas Anand");
		redeSocial.cadastra(0, "Teste1", "4321", "Teste 1");
		redeSocial.cadastra(0, "Teste2", "1111", "Teste 2");
		//dar tudo certo
		
		try {
			Thread.sleep(500);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		//fazendo uma postagem com o Teste1;
		System.out.println("Fazendo login com o Teste1");
		redeSocial.login("Teste1", "4321");
		redeSocial.fazPostagem(redeSocial.criaPostagem("Foto 2", 2));
		redeSocial.logout();
		System.out.println("Logout Teste1");
		
		try {
			Thread.sleep(500);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Nenhum login e postango img");
		//fazendo uma postagem sem nenhum login
		redeSocial.fazPostagem(redeSocial.criaPostagem("Foto 1", 1));
		System.out.println(redeSocial.mostrarMinhasPostagens());
		//falhar
		
		try {
			Thread.sleep(500);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Login Lucas");
		//fazendo algumas operaçoes com o Lucas
		redeSocial.login("Lucas", "1234");
		
		try {
			Thread.sleep(500);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Postanto foto");
		redeSocial.fazPostagem(redeSocial.criaPostagem("Foto 1", 1));
		System.out.println(redeSocial.mostrarMinhasPostagens());

		try {
			Thread.sleep(500);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Add amigo");
		redeSocial.addAmigo("Teste1");
		//adiciona amigo
		System.out.println(	redeSocial.verSeguidores());
		
		try {
			Thread.sleep(500);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Mudando o perfil");
		redeSocial.editarBio("Testando bio");
		//Atualizar a bio
		redeSocial.editarUserName("Teste1");
		//falha
		redeSocial.vePerfil();
		try {
			Thread.sleep(500);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("Mostrar Postagens");
		System.out.println(redeSocial.mostrarPostagem());
		System.out.println(redeSocial.mostrarMinhasPostagens());
		
		try {
			Thread.sleep(500);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Removendo amigo");
		redeSocial.removeAmigo("Teste1");
		System.out.println(redeSocial.verSeguidores());
		
		try {
			Thread.sleep(500);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		redeSocial.logout();
		System.out.println("logout");
		//sair da conta
		
		try {
			Thread.sleep(500);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		redeSocial.vePerfil();
		//falha
		
		
	}
}

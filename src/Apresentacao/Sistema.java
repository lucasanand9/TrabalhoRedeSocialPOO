package Apresentacao;

import negocios.RedeSocial;
import telas.Login;

public class Sistema {
	private static RedeSocial redeSocial = new RedeSocial();
	
	public static void main(String[] args) {
		redeSocial.cadastra(1, "Teste1", "4321", "Teste 1");
		redeSocial.cadastra(2, "Teste2", "1111", "Teste 2");
		redeSocial.cadastra(0, "Lucas", "1234", "Lucas Anand");
		redeSocial.cadastra(3, "a", "a", "a");

//		redeSocial.login("Lucas", "1234");
//		redeSocial.logout();

		//fazer botao de addPostagem e todo o resto relacionado a postagem(ver com o MAX)
		Login  log = new Login(redeSocial);
		log.setVisible(true);

		
	}
}

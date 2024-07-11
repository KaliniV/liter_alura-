package br.com.alura.literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import br.com.alura.literalura.principal.Principal;
import br.com.alura.literalura.service.LivroService;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner{
@Autowired
	private LivroService livroService;
	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(livroService);
		principal.exibeMenu();

}
}
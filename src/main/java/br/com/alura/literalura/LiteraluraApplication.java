package br.com.alura.literalura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.literalura.model.Dados;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConsumoApi consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://gutendex.com/books/?search=dom%20casmurro");
		System.out.println(json);
		ConverteDados conversor = new ConverteDados();
		Dados dados = conversor.obterDados(json, Dados.class);
		System.out.println(dados);
	}

}

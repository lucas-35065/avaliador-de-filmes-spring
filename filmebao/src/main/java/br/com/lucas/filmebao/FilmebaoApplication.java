package br.com.lucas.filmebao;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.lucas.filmebao.service.ConsumoApi;
import br.com.lucas.filmebao.service.ConverteDados;
import model.DadosSerie;

@SpringBootApplication
public class FilmebaoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FilmebaoApplication.class, args);
	}
	

@Override
public void run(String... args) throws Exception {
	var consumoApi = new ConsumoApi();
	var json = consumoApi.obterDados("http://www.omdbapi.com/?t=supernatural&apikey=85adf8ba");
	var conversor = new ConverteDados();
	DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
	System.out.println(dados);
}

}

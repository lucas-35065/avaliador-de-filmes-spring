package br.com.lucas.filmebao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.lucas.filmebao.service.ConsumoApi;
import br.com.lucas.filmebao.service.ConverteDados;
import br.com.lucas.filmebao.model.DadosEpisodio;
import br.com.lucas.filmebao.model.DadosSerie;
import br.com.lucas.filmebao.model.DadosTemporada;

@SpringBootApplication
public class FilmebaoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FilmebaoApplication.class, args);
	}
	

@Override
public void run(String... args) throws Exception {
	var consumoApi = new ConsumoApi();
	var json = consumoApi.obterDados("http://www.omdbapi.com/?t=bojack+horseman&apikey=85adf8ba");
	var conversor = new ConverteDados();
	DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);
	System.out.println(dadosSerie);
	json = consumoApi.obterDados("http://www.omdbapi.com/?t=bojack+horseman&season=1&episode=2&apikey=85adf8ba");	
	DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
	System.out.println(dadosEpisodio);

	List<DadosTemporada> listaTemporadas = new ArrayList<>();

	for(int i = 1; i <= dadosSerie.totalTemporadas(); i++){
		json = consumoApi.obterDados("http://www.omdbapi.com/?t=bojack+horseman&season=" + i + "&apikey=85adf8ba");
		DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
		listaTemporadas.add(dadosTemporada);
	}
	listaTemporadas.forEach(System.out::println);
}

}

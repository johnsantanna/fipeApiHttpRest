package br.com.fipeApiHttpRest;

import br.com.fipeApiHttpRest.models.Menu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FipeApiHttpRestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FipeApiHttpRestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String urlCompleta = "https://parallelum.com.br/fipe/api/v1/carros/marcas/59/modelos/5940/anos/2014-3";
		String urlEndereco = "https://parallelum.com.br/fipe/api/v1/";
		String urlTipoVeiculo = "";
		Integer urlCodigoMarca = 0;
		Integer urlCodigoModelo = 0;
		Integer urlAno = 0;

		//Menu.mostraMenu();
		Menu.opcaoMenu();

		System.out.println("FIM DO PROGRAMA!");

	}
}

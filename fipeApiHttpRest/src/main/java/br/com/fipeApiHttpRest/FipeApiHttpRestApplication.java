package br.com.fipeApiHttpRest;

import br.com.fipeApiHttpRest.principal.Principal;
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

		Principal principal = new Principal();
		principal.exibeMenu();
		System.out.println("FIM DO PROGRAMA!");

	}
}

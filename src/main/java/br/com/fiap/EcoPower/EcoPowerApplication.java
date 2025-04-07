package br.com.fiap.EcoPower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

//Alterar base packages aqui depois
@EnableMongoRepositories(basePackages = "br.com.fiap.repositorio")
@SpringBootApplication
public class EcoPowerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcoPowerApplication.class, args);
	}

}

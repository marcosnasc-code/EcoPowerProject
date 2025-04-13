package br.com.fiap.EcoPower;

import br.com.fiap.EcoPower.model.PermissionRoles;
import br.com.fiap.EcoPower.model.UsuarioModel;
import br.com.fiap.EcoPower.repository.UsuarioRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//Alterar base packages aqui depois
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@EnableMongoRepositories(basePackages = "br.com.fiap.EcoPower.repository")
@SpringBootApplication
public class EcoPowerApplication {

	public static void main(String[] args) {

		SpringApplication.run(EcoPowerApplication.class, args);

	}

}

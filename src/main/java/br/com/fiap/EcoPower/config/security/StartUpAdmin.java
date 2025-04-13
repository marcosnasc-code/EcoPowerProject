package br.com.fiap.EcoPower.config.security;

import br.com.fiap.EcoPower.model.PermissionRoles;
import br.com.fiap.EcoPower.model.UsuarioModel;
import br.com.fiap.EcoPower.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class StartUpAdmin {

    @Bean
    CommandLineRunner createAdminUser(UsuarioRepository usuarioRepository) {
        return args -> {
            if (usuarioRepository.findByEmail("admin@admin.com").isEmpty()) {
                UsuarioModel user = new UsuarioModel();
                user.setEmail("admin@admin.com");
                user.setNome("Administrador");
                user.setSenha(new BCryptPasswordEncoder().encode("senha123"));
                user.setRole(PermissionRoles.ADMIN);

                usuarioRepository.save(user);
                System.out.println("Usuário admin criado com sucesso!");
            } else {
                System.out.println("Usuário admin já existe.");
            }
        };
    }
}

package br.com.fiap.EcoPower.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private VerificarToken verificarToken;


    //FilterChain -- Sequencia de filtros de segurança no sistema
    @Bean
    public SecurityFilterChain filtrar_cadeia_deSeguranca(
            HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/registro/cliente").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/registro/empresa").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/cliente/listar").hasAnyRole("CLIENTE","ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/empresa/listar").hasAnyRole("EMPRESA","ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/cliente/{clienteEmail}/contratacao").hasAnyRole("CLIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/empresa/{empresaEmail}/servicos").hasAnyRole("EMPRESA", "ADMIN")
                        .requestMatchers(HttpMethod.GET,  "/api/ping").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/usuarios/listar/{email}").hasAnyRole("CLIENTE", "EMPRESA", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/cliente/atualizar").hasAnyRole("CLIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/empresa/atualizar").hasAnyRole("EMPRESA", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/deletar/{email}").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(
                        verificarToken, UsernamePasswordAuthenticationFilter.class
                )
                .build();

    }


    //Metodo Authentication Manager
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    //Metodo para encode/criptografia da senha do usuario
    @Bean
    public PasswordEncoder password_Encoder() {
        return new BCryptPasswordEncoder();
    }

}

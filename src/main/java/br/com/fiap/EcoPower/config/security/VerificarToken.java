package br.com.fiap.EcoPower.config.security;

import br.com.fiap.EcoPower.model.UsuarioModel;
import br.com.fiap.EcoPower.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class VerificarToken extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    protected void doFilterInternal(
            HttpServletRequest request, //dados da requisicao do user
            HttpServletResponse response, //dados da resposta devolvida ao user
            FilterChain filterChain) throws ServletException, IOException { //corrente de filtragem + lançamento de excessoes

        String authorizationHeader = request.getHeader("Autorization");
        //exemplo String do header -> Bearer ToKeN321421nncu21cm32iocn3oi2
        String token = "";

        if (authorizationHeader == null) {
            token = null;
        } else {
            token = authorizationHeader.replace("Bearer", "").trim(); //manipula a string para o bearer virar nada e o .trim limpa os espaços no inicio e final
            String login = tokenService.validarToken(token);
            Optional<UsuarioModel> usuario = usuarioRepository.findByEmail(login); //VERIFICAR SE ISSO FUNCIONA PARA O CASO DO USERDETAILS
            // ^ procura os UserDetails a partir do login do usuario para verificar as autoridades/permissões do usuario

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    usuario, //passando o usuário
                    null,
                    usuario.get().getAuthorities() //passando as autoridades/permissões do usuario
            );

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            //define a autenticação do usuário no contexto de segurança do Spring Security,
            //permitindo que o sistema reconheça que o usuário está autenticado.

        }

        filterChain.doFilter(request, response);

    }
}

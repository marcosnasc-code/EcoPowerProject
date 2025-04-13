package br.com.fiap.EcoPower.config.security;

import br.com.fiap.EcoPower.model.UsuarioModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {


    @Value("${minhaChaveSecreta}") //A chave secreta é buscada fora da aplicação. Nesse caso
    //ela ficará em application properties;
    private String palavra_secreta;

    public String gerarToken(UsuarioModel usuario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(palavra_secreta);
            String token = JWT
                    .create()
                    .withIssuer("EcoPower") //Emissor do Token
                    .withSubject(usuario.getEmail()) //login do usuario
                    .withExpiresAt(gerarDataDeExpiracao()) //tempo de expiração do token
                    .sign(algorithm); //construir algoritmo de acordo com o hash escolhido
                return token;
            } catch (JWTCreationException erro){
                throw new RuntimeException("Não foi possível gerar o Token!");
            }
    }


    public String validarToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(palavra_secreta); //define o algoritmo hash de criptografia pra passar o token
            return JWT //começa a construção do verificador JWT
                        .require(algorithm) //define o algoritmo de validacao da assinatura do token
                        .withIssuer("EcoPower") // especifica o emissor do token
                        .build() //finaliza a configuração do verificador e prepara pra validar o token
                        .verify(token) //verifica o token
                        .getSubject(); //retorna o subject - login do usuario
            } catch (JWTVerificationException erro){
            return "";
        }
    }


    public Instant gerarDataDeExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
        //retorna o horário.de agora.mais o adicional de X horas.de acordo com o fuso horário Y;
    }


}

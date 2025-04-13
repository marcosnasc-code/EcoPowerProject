package br.com.fiap.EcoPower.controller;

import br.com.fiap.EcoPower.config.security.TokenService;
import br.com.fiap.EcoPower.dto.*;
import br.com.fiap.EcoPower.model.UsuarioModel;
import br.com.fiap.EcoPower.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    //Criando um ponto para um manejador de autenticação

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDto){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                loginDto.email(), loginDto.senha());
        //recebendo o email e senha do usuario pelo link e criando um novo objeto tipo UsernamePassword


        Authentication auth = authenticationManager.authenticate(usernamePassword);
        //usando o objeto criado para fazer a autenticacao com o manejador criado anteriormente;
        String token = tokenService.gerarToken((UsuarioModel) auth.getPrincipal());
        //var token recebe o token gerado por gerarToken que está recebendo como parametro
        //auth.getPrincipal() -> retorna o objeto do usuário autenticado com seus atributos
        //(usuario_model) -> transforma a var auth em um objeto tipo usuario_model

        return ResponseEntity.ok(new TokenDTO(token));
        //se estiver tudo okay o metodo retorna o status de ok junto com o token.

    }

    @PostMapping("/registro/cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioClienteExibicaoDTO registrar(@RequestBody @Valid UsuarioClienteCadastroDTO usuarioClienteCadastroDTOCadastroDto){
        UsuarioClienteExibicaoDTO usuarioSalvo = null;
        usuarioSalvo = usuarioService.gravarCliente(usuarioClienteCadastroDTOCadastroDto);
        return usuarioSalvo;
    }

    @PostMapping("/registro/empresa")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioEmpresaExibicaoDTO registrar(@RequestBody @Valid UsuarioEmpresaCadastroDTO usuarioEmpresaCadastroDTO){
        UsuarioEmpresaExibicaoDTO usuarioSalvo = null;
        usuarioSalvo = usuarioService.gravarEmpresa(usuarioEmpresaCadastroDTO);
        return usuarioSalvo;
    }



}

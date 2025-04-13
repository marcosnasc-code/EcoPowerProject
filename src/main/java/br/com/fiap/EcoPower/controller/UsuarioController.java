package br.com.fiap.EcoPower.controller;


import br.com.fiap.EcoPower.dto.UsuarioClienteCadastroDTO;
import br.com.fiap.EcoPower.dto.UsuarioClienteExibicaoDTO;
import br.com.fiap.EcoPower.model.UsuarioModel;
import br.com.fiap.EcoPower.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/listar/{email}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioClienteExibicaoDTO listarUsuarioEmail(@PathVariable String email){
        return usuarioService.listarUsuarioEmail(email);
    }

    @DeleteMapping("/deletar/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable String email){
        usuarioService.excluir(email);
    }



}

package br.com.fiap.EcoPower.controller;


import br.com.fiap.EcoPower.dto.UsuarioClienteCadastroDTO;
import br.com.fiap.EcoPower.dto.UsuarioClienteExibicaoDTO;
import br.com.fiap.EcoPower.dto.UsuarioEmpresaCadastroDTO;
import br.com.fiap.EcoPower.dto.UsuarioEmpresaExibicaoDTO;
import br.com.fiap.EcoPower.model.UsuarioModel;
import br.com.fiap.EcoPower.repository.UsuarioRepository;
import br.com.fiap.EcoPower.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cadastro")
public class UsuarioCadastroController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/empresas")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioEmpresaExibicaoDTO cadastrarEmpresas(@Valid @RequestBody UsuarioEmpresaCadastroDTO empresaCadastroDTO){
        return usuarioService.gravarEmpresa(empresaCadastroDTO);
    }

    @PostMapping("/usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioClienteExibicaoDTO cadastrarCliente(@Valid @RequestBody UsuarioClienteCadastroDTO clienteCadastroDTO){
        return usuarioService.gravarCliente(clienteCadastroDTO);
    }

}

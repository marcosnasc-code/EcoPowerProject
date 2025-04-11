package br.com.fiap.EcoPower.controller;


import br.com.fiap.EcoPower.dto.ContratacaoCadastroDTO;
import br.com.fiap.EcoPower.dto.UsuarioClienteExibicaoDTO;
import br.com.fiap.EcoPower.model.ContratosAtivos;
import br.com.fiap.EcoPower.model.UsuarioModel;
import br.com.fiap.EcoPower.service.ContratacaoService;
import br.com.fiap.EcoPower.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
@PreAuthorize("hasRole('CLIENTE')")
public class ClienteController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ContratacaoService contratacaoService;


    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioClienteExibicaoDTO> listarTodosClientes(Pageable pageable){
        return usuarioService.listarTodosClientes(pageable);
    }

    @PostMapping("/{clienteEmail}/contratacao")
    public ResponseEntity<ContratosAtivos> contratarServico(@PathVariable String clienteEmail, @RequestBody ContratacaoCadastroDTO contratacaoCadastroDTO){

        try{
            ContratosAtivos contrato = contratacaoService.contratarServicos(
                    clienteEmail,
                    contratacaoCadastroDTO.empresaEmail(),
                    contratacaoCadastroDTO.servicoId(),
                    contratacaoCadastroDTO.imovelId(),
                    contratacaoCadastroDTO.quantiaKw()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(contrato);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(null);
        }

    }


}

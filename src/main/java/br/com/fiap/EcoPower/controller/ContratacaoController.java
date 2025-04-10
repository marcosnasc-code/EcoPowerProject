package br.com.fiap.EcoPower.controller;


import br.com.fiap.EcoPower.dto.ContratacaoCadastroDTO;
import br.com.fiap.EcoPower.model.ContratosAtivos;
import br.com.fiap.EcoPower.service.ContratacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes/{clienteEmail}/contratacoes")
public class ContratacaoController {

    @Autowired
    private ContratacaoService contratacaoService;

    @PostMapping
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

package br.com.fiap.EcoPower.controller;


import br.com.fiap.EcoPower.dto.ServicoCadastroDTO;
import br.com.fiap.EcoPower.model.UsuarioModel;
import br.com.fiap.EcoPower.service.CadastroServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empresas/{empresaEmail}/cadastroservico")
public class CadastrarServicoController {

    @Autowired
    private CadastroServicoService cadastroServicoService;

    @PostMapping
    public ResponseEntity<UsuarioModel.Servico> cadastrarServico(@PathVariable String empresaEmail, @RequestBody ServicoCadastroDTO servicoCadastroDTO){

        try{
            UsuarioModel.Servico servico = cadastroServicoService.cadastrarServico(
                    empresaEmail,
                    servicoCadastroDTO.idServico(),
                    servicoCadastroDTO.nome(),
                    servicoCadastroDTO.precoBaseKwh()
                    );
            return ResponseEntity.status(HttpStatus.CREATED).body(servico);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}

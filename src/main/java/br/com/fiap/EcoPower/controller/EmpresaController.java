package br.com.fiap.EcoPower.controller;


import br.com.fiap.EcoPower.dto.ServicoCadastroDTO;
import br.com.fiap.EcoPower.dto.UsuarioEmpresaExibicaoDTO;
import br.com.fiap.EcoPower.model.UsuarioModel;
import br.com.fiap.EcoPower.service.CadastroServicoService;
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
@RequestMapping("/api/empresa")
@PreAuthorize("hasRole('EMPRESA')")
public class EmpresaController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CadastroServicoService cadastroServicoService;

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioEmpresaExibicaoDTO> listarTodasEmpresas(Pageable pageable){
        return usuarioService.listarTodasEmpresas(pageable);
    }

    public ResponseEntity<UsuarioModel.Servico> cadastrarServico(@PathVariable String empresaEmail, @RequestBody ServicoCadastroDTO servicoCadastroDTO){

        try{
            UsuarioModel.Servico servico = cadastroServicoService.cadastrarServico(
                    empresaEmail,
                    servicoCadastroDTO.nome(),
                    servicoCadastroDTO.precoBaseKwh()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(servico);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}

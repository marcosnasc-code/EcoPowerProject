package br.com.fiap.EcoPower.controller;


import br.com.fiap.EcoPower.dto.ServicoCadastroDTO;
import br.com.fiap.EcoPower.dto.UsuarioEmpresaCadastroDTO;
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
@PreAuthorize("hasRole(EMPRESA)")
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

    @PostMapping("/{empresaEmail}/servicos")
    @ResponseStatus(HttpStatus.CREATED)
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

    @PutMapping("/atualizar")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioModel atualizarEmpresa(@RequestBody @Valid UsuarioEmpresaCadastroDTO usuarioEmpresaCadastroDTO){
        UsuarioModel usuarioModel = conversaoDtoModelEmpresa(usuarioEmpresaCadastroDTO);
        return usuarioService.atualizarEmpresa(usuarioModel);
    }

    private UsuarioModel conversaoDtoModelEmpresa(UsuarioEmpresaCadastroDTO usuarioEmpresaCadastroDTO){
        UsuarioModel usuarioModel = new UsuarioModel();

        //Mapeando dados basicos
        usuarioModel.setId(usuarioEmpresaCadastroDTO.id());
        usuarioModel.setNome(usuarioEmpresaCadastroDTO.nome());
        usuarioModel.setEmail(usuarioEmpresaCadastroDTO.email());
        usuarioModel.setSenha(usuarioEmpresaCadastroDTO.senha());

        //Mapeando Endereço
        if(usuarioEmpresaCadastroDTO.endereco() != null){
            UsuarioModel.Endereco endereco = new UsuarioModel.Endereco();
            endereco.setCep(usuarioEmpresaCadastroDTO.endereco().cep());
            endereco.setRegiao(usuarioEmpresaCadastroDTO.endereco().regiao());
            usuarioModel.setEndereco(endereco);
        }

        //Mapeando Dados Empresa
        if(usuarioEmpresaCadastroDTO.dadosEmpresa() != null){
            UsuarioModel.DadosEmpresa dadosEmpresa = new UsuarioModel.DadosEmpresa();

            dadosEmpresa.setCnpj(usuarioEmpresaCadastroDTO.dadosEmpresa().cnpj());

            usuarioModel.setDadosEmpresa(dadosEmpresa);
        }
        return usuarioModel;
    }


}

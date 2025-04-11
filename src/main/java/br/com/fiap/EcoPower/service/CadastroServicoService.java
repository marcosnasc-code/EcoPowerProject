package br.com.fiap.EcoPower.service;


import br.com.fiap.EcoPower.model.UsuarioModel;
import br.com.fiap.EcoPower.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class CadastroServicoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioModel.Servico cadastrarServico(String empresaEmail, String nome, double precoBaseKw){

        //Verificando se a empresa é válida
        UsuarioModel empresa = usuarioRepository.findByEmail(empresaEmail)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        //Criando novo servico disponível
        UsuarioModel.Servico novoServico = new UsuarioModel.Servico();
        novoServico.setIdServico(UUID.randomUUID().toString());
        novoServico.setNome(nome);
        novoServico.setPrecoBaseKwh(precoBaseKw);

        //Adicionando o servico a empresa
        if (empresa.getDadosEmpresa().getServicos() == null){
            empresa.getDadosEmpresa().setServicos(new ArrayList<>());
        }
        empresa.getDadosEmpresa().getServicos().add(novoServico);


        //Atualizando empresa
        usuarioRepository.save(empresa);

        return novoServico;
    }

}

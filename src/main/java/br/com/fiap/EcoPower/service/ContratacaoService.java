package br.com.fiap.EcoPower.service;

import br.com.fiap.EcoPower.model.ContratosAtivos;
import br.com.fiap.EcoPower.model.StatusPagamento;
import br.com.fiap.EcoPower.model.UsuarioModel;
import br.com.fiap.EcoPower.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class ContratacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public ContratosAtivos contratarServicos(String clienteEmail, String empresaEmail, String servicoId, String imovelId, double quantiaKw){

        //Validando se ambas as partes existem
        UsuarioModel cliente = usuarioRepository.findByEmail(clienteEmail)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        UsuarioModel empresa = usuarioRepository.findByEmail(empresaEmail)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));


        //Verificando se o cadastro do servico existe
        UsuarioModel.Servico servico = empresa.getDadosEmpresa()
                                                .getServicos()
                                                .stream().filter(s -> s.getIdServico().equals(servicoId))
                                                .findFirst()
                                                .orElseThrow(() -> new RuntimeException("Servico não encontrado"));


        //Verifica se o imovel pertence ao cliente
        UsuarioModel.Imovel imovel = cliente.getDadosCliente()
                                            .getImoveis()
                                            .stream().filter(i -> i.getImoveis().equals(imovelId))
                                            .findFirst()
                                            .orElseThrow(() -> new RuntimeException("Imovel não encontrado no perfil do cliente"));


        //Cria novo contrato
        ContratosAtivos novoContrato = new ContratosAtivos();
        novoContrato.setUsuario_Id(clienteEmail);
        novoContrato.setImovel_Id(imovelId);
        novoContrato.setQuantiaKw(quantiaKw);
        novoContrato.setTarifaAplicada(servico.getPrecoBaseKwh());
        //PARA IMPLEMENTACAO POSTERIOR => TAXAS DE ACORDO COM A REGIAO DO CLIENTE
        novoContrato.setValorTotalParaPagamento(quantiaKw * servico.getPrecoBaseKwh());
        novoContrato.setStatusPagamento(StatusPagamento.PENDENTE);


        //Adicionando o contrato ao cliente
        if (cliente.getContratosAtivos() == null){
            cliente.setContratosAtivos(new ArrayList<>());
        }
        cliente.getContratosAtivos().add(novoContrato);

        //Atualizando o cliente
        usuarioRepository.save(cliente);

        return novoContrato;
    }

}

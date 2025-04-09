package br.com.fiap.EcoPower.dto;

import br.com.fiap.EcoPower.model.BrazilRegions;
import br.com.fiap.EcoPower.model.UsuarioModel;

public record EnderecoExibicaoDTO(
        String cep,
        BrazilRegions regiao
) {
    public static EnderecoExibicaoDTO fromEndereco(UsuarioModel.Endereco endereco){
        return new EnderecoExibicaoDTO(
                endereco.getCep(),
                endereco.getRegiao());
    }
}

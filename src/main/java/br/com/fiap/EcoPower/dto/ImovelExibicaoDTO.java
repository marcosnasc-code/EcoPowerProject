package br.com.fiap.EcoPower.dto;

import br.com.fiap.EcoPower.model.TipoImoveis;
import br.com.fiap.EcoPower.model.UsuarioModel;

public record ImovelExibicaoDTO(

        TipoImoveis imoveis,
        EnderecoExibicaoDTO endereco
) {
    public static ImovelExibicaoDTO fromImovel(UsuarioModel.Imovel imovel){
        return new ImovelExibicaoDTO(
                imovel.getImoveis(),
                EnderecoExibicaoDTO.fromEndereco(imovel.getEndereco()));
    }
}

package br.com.fiap.EcoPower.dto;

import br.com.fiap.EcoPower.model.UsuarioModel;

public record ServicoExibicaoDTO(
        String idServico,
        String nome,
        double precoBaseKwh
) {
    public static ServicoExibicaoDTO fromServico(UsuarioModel.Servico servico){
        return new ServicoExibicaoDTO(servico.getIdServico(), servico.getNome(), servico.getPrecoBaseKwh());
    }
}

package br.com.fiap.EcoPower.dto;

import br.com.fiap.EcoPower.model.UsuarioModel;

import java.util.List;

public record DadosClienteDTO(
        String cpf,
        List<ImovelCadastroDTO> imoveis

) {
}

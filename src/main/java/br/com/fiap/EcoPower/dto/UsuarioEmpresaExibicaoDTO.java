package br.com.fiap.EcoPower.dto;

import br.com.fiap.EcoPower.model.UsuarioModel;

public record UsuarioEmpresaExibicaoDTO(
        String nome,
        String email,
        UsuarioModel.Endereco endereco,
        String cnpj
) {
    public UsuarioEmpresaExibicaoDTO(UsuarioModel usuarioModel){
        this(
                usuarioModel.getNome(),
                usuarioModel.getEmail(),
                usuarioModel.getEndereco(),
                usuarioModel.getDadosEmpresa().getCnpj()
        );
    }
}

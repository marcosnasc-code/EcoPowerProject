package br.com.fiap.EcoPower.dto;

import br.com.fiap.EcoPower.model.UsuarioModel;

public record UsuarioEmpresaExibicaoDTO(
        String id,
        String nome,
        String email,
        String senha,
        String cnpj
) {
    public UsuarioEmpresaExibicaoDTO(UsuarioModel usuarioModel){
        this(
                usuarioModel.getId(),
                usuarioModel.getNome(),
                usuarioModel.getEmail(),
                usuarioModel.getSenha(),
                usuarioModel.getDadosEmpresa().getCnpj()
        );
    }
}

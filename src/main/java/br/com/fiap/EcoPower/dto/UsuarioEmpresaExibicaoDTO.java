package br.com.fiap.EcoPower.dto;

import br.com.fiap.EcoPower.model.UsuarioModel;

public record UsuarioEmpresaExibicaoDTO(
        String nome,
        String email,
        String cnpj
) {
    public UsuarioEmpresaExibicaoDTO(UsuarioModel usuarioModel){
        this(
                usuarioModel.getNome(),
                usuarioModel.getEmail(),
                usuarioModel.getDadosEmpresa().getCnpj()
        );
    }
}

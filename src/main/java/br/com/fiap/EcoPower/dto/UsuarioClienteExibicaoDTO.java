package br.com.fiap.EcoPower.dto;

import br.com.fiap.EcoPower.model.UsuarioModel;

import java.util.List;

public record UsuarioClienteExibicaoDTO(
        String id,
        String nome,
        String email,
        String cpf

) {
        public UsuarioClienteExibicaoDTO(UsuarioModel usuarioModel){
            this(
                    usuarioModel.getId(),
                    usuarioModel.getNome(),
                    usuarioModel.getEmail(),
                    usuarioModel.getDadosCliente().getCpf()
            );
        }
}

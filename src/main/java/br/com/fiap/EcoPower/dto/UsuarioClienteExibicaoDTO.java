package br.com.fiap.EcoPower.dto;

import br.com.fiap.EcoPower.model.UsuarioModel;

import java.util.List;

public record UsuarioClienteExibicaoDTO(

        String nome,
        String email,
        String cpf,
        UsuarioModel.Endereco endereco,
        UsuarioModel.DadosCliente dadosCliente

) {
        public UsuarioClienteExibicaoDTO(UsuarioModel usuarioModel){
            this(
                    usuarioModel.getNome(),
                    usuarioModel.getEmail(),
                    usuarioModel.getDadosCliente().getCpf(),
                    usuarioModel.getEndereco(),
                    usuarioModel.getDadosCliente()
            );
        }
}

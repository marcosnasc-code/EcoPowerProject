package br.com.fiap.EcoPower.dto;

import br.com.fiap.EcoPower.model.TipoImoveis;
import br.com.fiap.EcoPower.model.UsuarioModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ImovelCadastroDTO(
        String idImovel,

        @NotBlank(message = "O tipo de imóvel precisa ser informado!")
        @NotNull
        TipoImoveis tipoImoveis,

        @NotNull(message = "O endereço precisa ser inserido para verificação de taxas!")
        @Valid
        EnderecoCadastroDTO endereco
) {
}

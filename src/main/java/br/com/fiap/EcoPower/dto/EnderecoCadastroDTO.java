package br.com.fiap.EcoPower.dto;

import br.com.fiap.EcoPower.model.BrazilRegions;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoCadastroDTO(

        @NotBlank(message = "O CEP precisa ser informado!")
        String cep,

        @NotNull (message = "Região é obrigatória")
        BrazilRegions regiao

) {
}

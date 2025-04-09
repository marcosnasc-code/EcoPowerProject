package br.com.fiap.EcoPower.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ServicoCadastroDTO(
        String idServico,

        @NotBlank(message = "Nome do serviço precisa ser inserido!") //"ENERGIA_SOLAR", "ENERGIA_EOLICA"
        @NotNull
        String nome,

        @NotBlank(message = "O preço base do Kw/h precisa ser inserido!")
        @NotNull
        double precoBaseKwh
) {
}

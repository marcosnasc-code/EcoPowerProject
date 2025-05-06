package br.com.fiap.EcoPower.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ServicoCadastroDTO(
//        String idServico,

        @NotBlank(message = "Nome do serviço precisa ser inserido!") //"ENERGIA_SOLAR", "ENERGIA_EOLICA"
        @NotNull(message = "Nome do serviço obrigatório")
        @Valid
        String nome,

        @NotBlank(message = "O preço base do Kw/h precisa ser inserido!")
        @NotNull(message = "preco obrigatório")
        @Positive(message = "O valor deve ser positivo")
        @Valid
        double precoBaseKwh
) {
}

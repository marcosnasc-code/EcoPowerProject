package br.com.fiap.EcoPower.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ContratacaoCadastroDTO(

        @NotBlank(message = "Email da empresa é obrigatario")
        String empresaEmail,

        @NotBlank(message = "ID do serviço é obrigatório")
        String servicoId,

        @NotBlank(message = "ID do imovel é obrigatorio")
        String imovelId,

        @Positive(message = "Quantia de KW deve ser positiva")
        double quantiaKw

) {
}

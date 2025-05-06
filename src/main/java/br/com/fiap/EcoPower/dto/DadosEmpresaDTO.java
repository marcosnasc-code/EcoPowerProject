package br.com.fiap.EcoPower.dto;

import jakarta.validation.constraints.NotNull;

public record DadosEmpresaDTO(

        @NotNull(message = "Regiao é obrigatória")
        String cnpj

) {
}

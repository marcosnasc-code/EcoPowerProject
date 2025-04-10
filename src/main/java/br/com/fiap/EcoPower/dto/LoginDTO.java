package br.com.fiap.EcoPower.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(

        @NotBlank(message = "O email do usuário é obrigatório!")
        @Email(message = "Email em formato inválido!")
        String email,

        @NotBlank(message = "A senha do usuário é obrigatória!")
        @Size(min = 6, max = 10, message = "A senha precisa ter entre 6 e 10 caracteres!")
        String senha
) {
}

package br.com.fiap.EcoPower.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UsuarioEmpresaCadastroDTO(

        String id,

        @NotBlank(message = "Nome do usuario deve ser inserido!")
        @NotNull
        String nome,

        @NotBlank(message = "Email do usuário precisa ser inserido!")
        @Email(message = "Insira um e-mail com o formato correto!")
        String email,

        @NotBlank(message = "Senha do usuário precisa ser inserida!")
        @Size(min = 6, max = 12, message = "A senha deve conter entre 6 e 12 caracteres!")
        String senha,

        @Valid
        EnderecoCadastroDTO endereco,

        @Valid
        DadosEmpresaDTO dadosEmpresa

) {
}

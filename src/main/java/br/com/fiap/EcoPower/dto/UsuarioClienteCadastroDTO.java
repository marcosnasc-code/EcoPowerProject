package br.com.fiap.EcoPower.dto;

import br.com.fiap.EcoPower.model.PermissionRoles;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UsuarioClienteCadastroDTO(

        String id,

        @NotBlank(message = "Nome do usuário deve ser inserido!")
        @NotNull
        String nome,

        @NotBlank(message = "Email do usuário precisa ser inserido!")
        @Email(message = "Insira um e-mail com o formato correto!")
        String email,

        @NotBlank(message = "Senha do usuário precisa ser inserida!")
        @Size(min = 6, max = 12, message = "A senha deve conter entre 6 e 12 caracteres!")
        String senha,

        @NotNull(message = "O endereço deve ser informado!")
        @Valid
        EnderecoCadastroDTO endereco,

        @NotNull(message = "Os dados do cliente devem ser preenchidos!")
        @Valid
        DadosClienteDTO dadosCliente
) {
}

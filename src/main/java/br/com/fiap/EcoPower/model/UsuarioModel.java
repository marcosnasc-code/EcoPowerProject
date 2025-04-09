package br.com.fiap.EcoPower.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "usuarios") //indica coleção no mongoDB
public class UsuarioModel implements UserDetails {

    @Id //Anotação para PK no mongoDB
    private String id;

    private String nome;
    private String email;
    private String senha;
    private Endereco endereco;

//    @Enumerated(EnumType.STRING)
//    private BrazilRegions regiao; //SUDESTE, NORDESTE, SUL, NORTE, CENTRO_OESTE
//    @Enumerated(EnumType.STRING)
//    private StatusUsers status; //ATIVO, INATIVO
    @Enumerated(EnumType.STRING)
    private PermissionRoles role; //CLIENTE, DISTRIBUIDOR, ADMIN

    @Indexed
    private List<ContratosAtivos> contratosAtivos;



    //subdocumentos Usuario

    //Cliente
    private DadosCliente dadosCliente;
    //Empresa
    private DadosEmpresa dadosEmpresa;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == PermissionRoles.ADMIN){
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_CLIENTE"),
                    new SimpleGrantedAuthority("ROLE_EMPRESA")
            );
        }else {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_CLIENTE")
            );
        }
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    //InnerClass para subdocumentos
    @Getter
    @Setter
    public static class Endereco{
        private String cep;

        @Enumerated(EnumType.STRING)
        private BrazilRegions regiao; //SUDESTE, NORDESTE, SUL, NORTE, CENTRO_OESTE
    }

    @Getter
    @Setter
    public static class DadosCliente {
        private String cpf;

        @Indexed
        private List<Imovel> imoveis; // Lista de imóveis do cliente
    }

    @Getter
    @Setter
    public static class DadosEmpresa {
        private String cnpj;
        private List<Servico> servicos; // Lista de serviços oferecidos
    }

    @Getter
    @Setter
    public static class Imovel {
        private String idImovel = UUID.randomUUID().toString();
        private TipoImoveis imoveis; // "CASA", "APARTAMENTO", "COMERCIAL"
        private Endereco endereco; // Endereço do imóvel
    }

    @Getter @Setter
    public static class Servico {
        private String idServico = UUID.randomUUID().toString();
        private String nome; // "ENERGIA_SOLAR", "ENERGIA_EOLICA"
        private double precoBaseKwh;
    }


}

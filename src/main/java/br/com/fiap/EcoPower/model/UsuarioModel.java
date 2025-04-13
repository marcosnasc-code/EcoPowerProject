package br.com.fiap.EcoPower.model;

//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "usuarios") //indica coleção no mongoDB
public class UsuarioModel implements UserDetails {

    @Id //Anotação para PK no mongoDB
    private String id;

    private String nome;

    @Indexed(unique = true)
    private String email;
    private String senha;
    private Endereco endereco;

//    @Enumerated(EnumType.STRING)
//    private BrazilRegions regiao; //SUDESTE, NORDESTE, SUL, NORTE, CENTRO_OESTE
//    @Enumerated(EnumType.STRING)
//    private StatusUsers status; //ATIVO, INATIVO
//    @Enumerated(EnumType.STRING)
    private PermissionRoles role; //CLIENTE, DISTRIBUIDOR, ADMIN



    //subdocumentos Usuario

    //Cliente
    private DadosCliente dadosCliente;
    //Empresa
    private DadosEmpresa dadosEmpresa;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (this.role) {
            case ADMIN -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_CLIENTE"),
                    new SimpleGrantedAuthority("ROLE_EMPRESA")
            );
            case EMPRESA -> List.of(
                    new SimpleGrantedAuthority("ROLE_EMPRESA")
            );
            case CLIENTE -> List.of(
                    new SimpleGrantedAuthority("ROLE_CLIENTE")
            );
            default -> throw new IllegalStateException("Role inválida: " + this.role);
        };
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DadosEmpresa getDadosEmpresa() {
        return dadosEmpresa;
    }

    public void setDadosEmpresa(DadosEmpresa dadosEmpresa) {
        this.dadosEmpresa = dadosEmpresa;
    }

    public DadosCliente getDadosCliente() {
        return dadosCliente;
    }

    public void setDadosCliente(DadosCliente dadosCliente) {
        this.dadosCliente = dadosCliente;
    }

//    public List<ContratosAtivos> getContratosAtivos() {
//        return contratosAtivos;
//    }
//
//    public void setContratosAtivos(List<ContratosAtivos> contratosAtivos) {
//        this.contratosAtivos = contratosAtivos;
//    }

    public PermissionRoles getRole() {
        return role;
    }

    public void setRole(PermissionRoles role) {
        this.role = role;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    //InnerClass para subdocumentos
    public static class Endereco{
        private String cep;

//        @Enumerated(EnumType.STRING)
        private BrazilRegions regiao; //SUDESTE, NORDESTE, SUL, NORTE, CENTRO_OESTE

        public String getCep() {
            return cep;
        }

        public void setCep(String cep) {
            this.cep = cep;
        }

        public BrazilRegions getRegiao() {
            return regiao;
        }

        public void setRegiao(BrazilRegions regiao) {
            this.regiao = regiao;
        }
    }

    public static class DadosCliente {
        private String cpf;

        @Indexed
        private List<Imovel> imoveis; // Lista de imóveis do cliente

        @Indexed
        private List<ContratosAtivos> contratosAtivos;

        public List<ContratosAtivos> getContratosAtivos() {
            return contratosAtivos;
        }

        public void setContratosAtivos(List<ContratosAtivos> contratosAtivos) {
            this.contratosAtivos = contratosAtivos;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public List<Imovel> getImoveis() {
            return imoveis;
        }

        public void setImoveis(List<Imovel> imoveis) {
            this.imoveis = imoveis;
        }
    }

    public static class DadosEmpresa {
        private String cnpj;
        private List<Servico> servicos; // Lista de serviços oferecidos

        public String getCnpj() {
            return cnpj;
        }

        public void setCnpj(String cnpj) {
            this.cnpj = cnpj;
        }

        public List<Servico> getServicos() {
            return servicos;
        }

        public void setServicos(List<Servico> servicos) {
            this.servicos = servicos;
        }
    }


    public static class Imovel {
        private String idImovel = UUID.randomUUID().toString();
        private TipoImoveis imoveis; // "CASA", "APARTAMENTO", "COMERCIAL"
        private Endereco endereco; // Endereço do imóvel

        public String getIdImovel() {
            return idImovel;
        }

        public void setIdImovel(String idImovel) {
            this.idImovel = idImovel;
        }

        public Endereco getEndereco() {
            return endereco;
        }

        public void setEndereco(Endereco endereco) {
            this.endereco = endereco;
        }

        public TipoImoveis getImoveis() {
            return imoveis;
        }

        public void setImoveis(TipoImoveis imoveis) {
            this.imoveis = imoveis;
        }
    }

    //Separar depois, criar própria classe.

    public static class Servico {
        private String idServico = UUID.randomUUID().toString();
        private String nome; // "ENERGIA_SOLAR", "ENERGIA_EOLICA"
        private double precoBaseKwh;

        public String getIdServico() {
            return idServico;
        }

        public void setIdServico(String idServico) {
            this.idServico = idServico;
        }

        public double getPrecoBaseKwh() {
            return precoBaseKwh;
        }

        public void setPrecoBaseKwh(double precoBaseKwh) {
            this.precoBaseKwh = precoBaseKwh;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }

}

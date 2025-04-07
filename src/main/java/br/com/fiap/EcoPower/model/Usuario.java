package br.com.fiap.EcoPower.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "usuarios") //indica coleção no mongoDB
public class Usuario {

    @Id //Anotação para PK no mongoDB
    private String id;

    private String nome;
    private String cpf;
    private String Telefone;
    private LocalDate dataNascimento;
    private int ecoPoints;
    private float metaEconomia;
    private String senha;

    @Enumerated(EnumType.STRING)
    private BrazilRegions regiao; //SUDESTE, NORDESTE, SUL, NORTE, CENTRO_OESTE
    @Enumerated(EnumType.STRING)
    private StatusUsers status; //ATIVO, INATIVO
    @Enumerated(EnumType.STRING)
    private PermissionRoles role; //CLIENTE, DISTRIBUIDOR, ADMIN

    @Indexed
    private List<ContratosAtivos> contratosAtivos;
    @Indexed
    private List<Imoveis> imoveis;


}

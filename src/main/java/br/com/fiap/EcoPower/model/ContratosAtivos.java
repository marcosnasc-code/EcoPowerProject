package br.com.fiap.EcoPower.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class ContratosAtivos {

    @Id
    private String contrato_Id;

    private String usuario_Id;
    private String imovel_Id;
    private double quantiaKw;
    private LocalDate dataInicio = LocalDate.now();
    private double tarifaAplicada;
    private double valorTotalParaPagamento;

    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;


    public ContratosAtivos() {
        this.contrato_Id = UUID.randomUUID().toString();
    }
}

package br.com.fiap.EcoPower.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

import java.time.LocalDate;


public class Pagamentos {

    @Id
    private String pagamentoId;

    private String usuarioId;
    private String contratoId;
    private float valor;
    private LocalDate dataCriacao = LocalDate.now();
    private LocalDate dataPagamento = LocalDate.now();


    @Enumerated(EnumType.STRING)
    private MetodosPagamento metodosPagamento;
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;



}

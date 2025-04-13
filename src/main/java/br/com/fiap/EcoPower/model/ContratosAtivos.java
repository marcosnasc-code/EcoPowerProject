package br.com.fiap.EcoPower.model;

//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;


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

//    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;


    public ContratosAtivos() {
        this.contrato_Id = UUID.randomUUID().toString();
    }

    public String getContrato_Id() {
        return contrato_Id;
    }

    public void setContrato_Id(String contrato_Id) {
        this.contrato_Id = contrato_Id;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public double getValorTotalParaPagamento() {
        return valorTotalParaPagamento;
    }

    public void setValorTotalParaPagamento(double valorTotalParaPagamento) {
        this.valorTotalParaPagamento = valorTotalParaPagamento;
    }

    public double getTarifaAplicada() {
        return tarifaAplicada;
    }

    public void setTarifaAplicada(double tarifaAplicada) {
        this.tarifaAplicada = tarifaAplicada;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public double getQuantiaKw() {
        return quantiaKw;
    }

    public void setQuantiaKw(double quantiaKw) {
        this.quantiaKw = quantiaKw;
    }

    public String getImovel_Id() {
        return imovel_Id;
    }

    public void setImovel_Id(String imovel_Id) {
        this.imovel_Id = imovel_Id;
    }

    public String getUsuario_Id() {
        return usuario_Id;
    }

    public void setUsuario_Id(String usuario_Id) {
        this.usuario_Id = usuario_Id;
    }
}

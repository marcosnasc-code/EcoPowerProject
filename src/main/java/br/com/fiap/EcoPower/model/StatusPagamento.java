package br.com.fiap.EcoPower.model;

public enum StatusPagamento {

    INICIADO("iniciado"),
    PENDENTE("pendente"),
    AUTORIZADO("autorizado"),
    CONCLUIDO("concluido"),
    CANCELADO("cancelado");

    private String statusPagamento;

    StatusPagamento(String statusPagamento){
        this.statusPagamento = statusPagamento;
    }

    public String getStatusPagamento(){
        return statusPagamento;
    }




}

package br.com.fiap.EcoPower.model;

public enum MetodosPagamento {

    CREDITO("credito"),
    DEBITO("debito"),
    BOLETO("boleto"),
    PIX("pix");

    private String metodoPagamento;

    MetodosPagamento(String metodoPagamento){
        this.metodoPagamento = metodoPagamento;
    }

    public String getMetodoPagamento(){
        return metodoPagamento;
    }

}

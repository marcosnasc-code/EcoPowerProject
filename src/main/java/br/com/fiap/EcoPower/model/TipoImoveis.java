package br.com.fiap.EcoPower.model;

public enum TipoImoveis {

    RESIDENCIAL("residencial"),
    COMERCIAL("comercial");

    private String imovel;

    TipoImoveis(String imovel){
        this.imovel = imovel;
    }

    public String getImovel(){
        return imovel;
    }

}

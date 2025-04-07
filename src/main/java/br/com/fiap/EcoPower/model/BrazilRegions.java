package br.com.fiap.EcoPower.model;

public enum BrazilRegions {

    SUDESTE("sudeste"),
    CENTRO_OESTE("centro-oeste"),
    SUL("sul"),
    NORDESTE("nordeste"),
    NORTE("norte");

    private String regiao;

    BrazilRegions(String regiao){
        this.regiao = regiao;
    }

    public String getRegiao(){
        return regiao;
    }

}

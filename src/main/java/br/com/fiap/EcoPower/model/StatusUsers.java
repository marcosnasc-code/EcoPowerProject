package br.com.fiap.EcoPower.model;

public enum StatusUsers {

    ATIVO("ativo"),
    INATIVO("inativo");


    private String status;

    StatusUsers(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

}

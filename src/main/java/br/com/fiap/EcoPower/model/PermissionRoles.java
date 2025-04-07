package br.com.fiap.EcoPower.model;

public enum PermissionRoles {

    CLIENTE("cliente"),
    DISTRIBUIDOR("distribuidor"),
    ADMIN("admin");

    private String role;

    PermissionRoles(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }


}

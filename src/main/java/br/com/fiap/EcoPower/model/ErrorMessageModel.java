package br.com.fiap.EcoPower.model;


import java.util.List;

public class ErrorMessageModel {

    private String message;
    private List<String> details;

    public ErrorMessageModel(String message, List<String> details) {
        this.message = message;
        this.details = details;
    }

    // Getters e Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}

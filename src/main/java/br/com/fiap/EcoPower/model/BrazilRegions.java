package br.com.fiap.EcoPower.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BrazilRegions {
    SUDESTE, SUL, CENTRO_OESTE, NORTE, NORDESTE;

    @JsonCreator
    public static BrazilRegions from(String value) {
        for (BrazilRegions regiao : values()) {
            if (regiao.name().equalsIgnoreCase(value)) {
                return regiao;
            }
        }
        throw new IllegalArgumentException("Região inválida: " + value);
    }

    @JsonValue
    public String getValue() {
        return name();
    }
}

package br.com.fiap.EcoPower.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Imoveis {

    private String id;
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    private TipoImoveis imovel; //RESIDENCIAL, COMERCIAL

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class Endereco {
        private String regiao;
        private String cep;
    }
}

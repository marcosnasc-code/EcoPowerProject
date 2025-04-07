package br.com.fiap.EcoPower.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Document(collection = "empresa")
public class Empresa {

    @Id
    private String id;

    private String email;
    private String cnpj;
    private String telefone;
    private String senha;
    private Servico_Disponibilizado servicoDisponibilizado;

    @Enumerated(EnumType.STRING)
    private StatusUsers status;


    public static class Servico_Disponibilizado{
        private String tipoEnergia;
        private double precoServico;

        public Servico_Disponibilizado(String tipoEnergia, double precoServico) {
            this.tipoEnergia = tipoEnergia;
            this.precoServico = precoServico;
        }
    }


}

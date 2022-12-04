package com.redes.redes.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    private TipoVeiculo tipoVeiculo;

    private Integer carga;
    private Integer distEmRodoviaPavimentada;
    private Integer distEmRodoviaNaoPavimentada;
    private Float custoTotal;

    public Veiculo(Veiculo veiculo) {
        Veiculo novoVeiculo = new Veiculo();
        novoVeiculo.setId(veiculo.getId());
        novoVeiculo.setTipoVeiculo(veiculo.getTipoVeiculo());
        novoVeiculo.setDistEmRodoviaPavimentada(veiculo.getDistEmRodoviaPavimentada());
        novoVeiculo.setDistEmRodoviaNaoPavimentada(veiculo.getDistEmRodoviaNaoPavimentada());
        novoVeiculo.setCarga(veiculo.getCarga());
        Float custoTotal = (veiculo.getDistEmRodoviaPavimentada() * 0.63f
                + veiculo.getDistEmRodoviaNaoPavimentada() * 0.72f)
                * veiculo.getTipoVeiculo().getFatorDeMultiplicacao();
        if (veiculo.getCarga() > 5) {
            int diferencaDoLimite = veiculo.getCarga() - 5;
            int somaKm = (veiculo.getDistEmRodoviaPavimentada() + veiculo.getDistEmRodoviaNaoPavimentada());
            custoTotal += diferencaDoLimite * 0.03f * somaKm;
        }

        novoVeiculo.setCustoTotal(custoTotal);
    }

    @ManyToOne()
    private Usuario usuario;

}
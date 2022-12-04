package com.redes.redes.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.redes.redes.Model.TipoVeiculo;

@Repository
public class TipoVeiculoRepository {

    private List<TipoVeiculo> listTipoVeiculos = new ArrayList<TipoVeiculo>(
            List.of(new TipoVeiculo(0l, "Veículo urbano de carga (VUC)", 1.0f),
                    new TipoVeiculo(1l, "Caminhão 3/4", 1.05f),
                    new TipoVeiculo(2l, "Caminhão toco", 1.08f),
                    new TipoVeiculo(3l, "Carreta simples", 1.13f),
                    new TipoVeiculo(4l, "Carreta eixo estendido", 1.19f)));

    public List<TipoVeiculo> getAllTipoVeiculos() {
        return listTipoVeiculos;
    }

    public TipoVeiculo updateFatorMultiplicacao(Long id, Float fatorMultiplicacao) {
        TipoVeiculo tipo = null;
        try {
            tipo = listTipoVeiculos.stream().filter(obj -> obj.getId().equals(id)).findFirst()
                    .orElseThrow(RuntimeException::new);
            tipo.setFatorDeMultiplicacao(fatorMultiplicacao);
        } catch (RuntimeException e) {
            System.out.println("Não encontrado !");
        }

        return tipo;
    }

}
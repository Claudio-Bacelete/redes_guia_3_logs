package com.redes.redes.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.redes.redes.Model.Veiculo;

@Repository
public class VeiculoRepository {
    private List<Veiculo> listVeiculos = new ArrayList<Veiculo>();
    private Long count = 0l;

    public List<Veiculo> getAllVeiculos() {
        return listVeiculos;
    }

    public Veiculo save(Veiculo veiculo) {
        veiculo.setId(count++);
        listVeiculos.add(veiculo);
        return veiculo;
    }

    public void delete(Long id) {
        listVeiculos.removeIf(veiculo -> veiculo.getId().equals(id));
    }

    public Veiculo update(Veiculo veiculo, Long id) {
        int count = 0;
        for (Veiculo iterator : listVeiculos) {
            if (iterator.getId().equals(id)) {
                veiculo.setId(id);
                listVeiculos.set(count, veiculo);
                return veiculo;
            }
            count++;

        }
        return null;

    }

}

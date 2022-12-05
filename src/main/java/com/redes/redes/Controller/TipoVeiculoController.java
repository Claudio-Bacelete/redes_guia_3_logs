package com.redes.redes.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redes.redes.Data.DetalheUsuario;
import com.redes.redes.Model.TipoVeiculo;
import com.redes.redes.Services.DetalheUsuarioServiceImpl;
import com.redes.redes.Services.TipoVeiculoService;
import com.redes.redes.Utils.WriteFile;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tipo")
public class TipoVeiculoController {

    @Autowired
    TipoVeiculoService service;

    @Autowired
    WriteFile writeFile;

    @Autowired
    private DetalheUsuarioServiceImpl usuarioService;

    @ApiOperation(value = "Listar todos os tipos de Veículo com Fatores de Multiplicação")
    @GetMapping()
    public List<TipoVeiculo> findAllTipoVeiculos(Authentication auth) throws IOException {
        DetalheUsuario usuario = usuarioService.loadUserByUsername(auth.getName());
        List<TipoVeiculo> listTipo = service.getAllTipoVeiculos();
        writeFile.writeFile(
                "Usuário " + usuario.getUsername() + " solicitou listagem de tipos de veículos \n"
                        + listTipo.toString());
        return listTipo;
    }

    @Transactional
    @ApiOperation(value = "Atualizar o fator de multiplicação de um tipo de veículo")
    @PostMapping(value = "/atualizarFator/{id}")
    public TipoVeiculo updateFatorMultiplicacao(@PathVariable(name = "id") Long id,
            @RequestBody Float fatorMultiplicacao, Authentication auth) throws IOException {
        DetalheUsuario usuario = usuarioService.loadUserByUsername(auth.getName());
        TipoVeiculo tipoVeiculo = service.updateFatorMultiplicacao(id, fatorMultiplicacao);
        writeFile.writeFile("Usuário " + usuario.getUsername() + " atualizou o fator de multiplicação do veículo \n"
                + tipoVeiculo.toString());
        return tipoVeiculo;
    }

}

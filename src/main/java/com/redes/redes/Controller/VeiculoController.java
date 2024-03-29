package com.redes.redes.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redes.redes.DTO.VeiculoDTO;
import com.redes.redes.Data.DetalheUsuario;
import com.redes.redes.Model.Veiculo;
import com.redes.redes.Services.DetalheUsuarioServiceImpl;
import com.redes.redes.Services.VeiculoService;
import com.redes.redes.Utils.WriteFile;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @Autowired
    private DetalheUsuarioServiceImpl usuarioService;

    @Autowired
    WriteFile writeFile;

    @PostMapping
    @ApiOperation(value = "Adicionar valores de Veículo")
    public Veiculo addVeiculo(Authentication auth, @RequestBody VeiculoDTO veiculoDTO) throws IOException {
        DetalheUsuario usuario = usuarioService.loadUserByUsername(auth.getName());
        Veiculo veiculo = service.saveVeiculo(veiculoDTO, usuario);
        writeFile.writeFile("Usuário " + usuario.getUsername() + " criou o veículo \n" + veiculo.toString());
        return veiculo;
    }

    @GetMapping
    @ApiOperation(value = "Listar todos os Veículos")
    public List<Veiculo> findAllVeiculos(Authentication auth) throws IOException {
        DetalheUsuario usuario = usuarioService.loadUserByUsername(auth.getName());
        List<Veiculo> listVeiculos = service.getAllVeiculos();
        writeFile.writeFile("Usuário " + usuario.getUsername() + " solicitou listagem de tipos de veículo \n"
                + listVeiculos.toString());
        return listVeiculos;
    }

    @PostMapping(value = "/atualizar/{id}")
    @ApiOperation(value = "Atualizar valor do veículo")
    public Veiculo updateVeiculo(@PathVariable(name = "id") Long id, @RequestBody VeiculoDTO dto,
            Authentication auth)
            throws IOException {
        DetalheUsuario usuario = usuarioService.loadUserByUsername(auth.getName());
        Veiculo veiculo = service.updateVeiculo(dto, id);
        writeFile.writeFile("Usuário " + usuario.getUsername() + "atualizou o veículo \n" + veiculo.toString());
        return veiculo;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar o valor do veículo")
    public String deleteVeiculo(@PathVariable long id, Authentication auth) throws IOException {
        DetalheUsuario usuario = usuarioService.loadUserByUsername(auth.getName());
        writeFile.writeFile("Usuário " + usuario.getUsername() + "deletou o veículo de Id " + id);
        return service.deletarVeiculo(id);
    }

}

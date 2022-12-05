package com.redes.redes.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redes.redes.DTO.UsuarioDTO;
import com.redes.redes.Model.EmailDetails;
import com.redes.redes.Model.Usuario;
import com.redes.redes.Services.EmailService;
import com.redes.redes.Services.UsuarioService;
import com.redes.redes.Utils.WriteFile;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @Autowired
    EmailService emailService;

    @Autowired
    WriteFile writeFile;

    @PostMapping(value = "/cadastrar")
    @ApiOperation(value = "Adicionar valores de Usuário")
    public Usuario addUsuario(@RequestBody UsuarioDTO dto) throws IOException {
        Usuario usuario = service.saveUsuario(dto);
        EmailDetails details = new EmailDetails(dto.getEmail(), "Seja Bem vindo !",
                "Usuário Cadastrado !");
        emailService.sendSimpleMail(details);
        writeFile.writeFile("Usuário " + usuario.getEmail() + " foi cadastrado ! \n");
        return usuario;
    }

}

package com.redes.redes.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.redes.redes.DTO.UsuarioDTO;
import com.redes.redes.Model.Usuario;
import com.redes.redes.Repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    PasswordEncoder encoder;

    public List<Usuario> getAllUsuarios() {
        return repository.getAllUsuarios();
    }

    public Usuario saveUsuario(UsuarioDTO dto) {
        Usuario usuario = DTOToEntity(dto);
        return repository.save(usuario);
    }

    public Usuario DTOToEntity(UsuarioDTO dto) {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(dto.getEmail());
        novoUsuario.setSenha(encoder.encode(dto.getSenha()));
        return novoUsuario;
    }

}

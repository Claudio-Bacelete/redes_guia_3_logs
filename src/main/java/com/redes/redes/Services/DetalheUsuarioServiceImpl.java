package com.redes.redes.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.redes.redes.Data.DetalheUsuario;
import com.redes.redes.Model.Usuario;
import com.redes.redes.Repository.UsuarioRepository;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public DetalheUsuario loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByEmail(username);
        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Email não encontrado !\n");
        }
        return new DetalheUsuario(usuario);
    }
}

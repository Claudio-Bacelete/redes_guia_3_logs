package com.redes.redes.Config.Security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redes.redes.Data.DetalheUsuario;
import com.redes.redes.Model.Usuario;
import com.redes.redes.Utils.JWTUtil;

public class JWTAuthFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager manager;

    public JWTAuthFilter(AuthenticationManager manager) {
        this.manager = manager;
    }

    private String algoritmo = JWTUtil.algoritmo;

    private Integer expiracao = JWTUtil.expiracao;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
            return manager.authenticate(
                    new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException("Erro na autenticação !\n");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        DetalheUsuario detalheUsuario = (DetalheUsuario) authResult.getPrincipal();
        String token = JWT.create().withSubject(detalheUsuario.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiracao))
                .sign(Algorithm.HMAC512(algoritmo));

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}

package com.redes.redes.Config.Security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.redes.redes.Utils.JWTUtil;

public class JWTValidateFilter extends BasicAuthenticationFilter {
    public static final String HEADER = "Authorization";
    public static final String PREFIXO = "Bearer ";

    private String algoritmo = JWTUtil.algoritmo;

    public JWTValidateFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String atributo = request.getHeader(HEADER);

        if (Objects.isNull(atributo) || !atributo.startsWith(PREFIXO)) {
            chain.doFilter(request, response);
            return;
        }
        String token = atributo.replace(PREFIXO, "");
        UsernamePasswordAuthenticationToken authenticationToken = getAuthToken(token);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthToken(String token) {
        String email = JWT.require(Algorithm.HMAC512(algoritmo)).build()
                .verify(token)
                .getSubject();

        if (Objects.isNull(email)) {
            return null;
        }
        return new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());

    }
}

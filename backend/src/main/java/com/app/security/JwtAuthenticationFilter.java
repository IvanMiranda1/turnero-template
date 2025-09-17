package com.app.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.lang.NonNull;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/***
 * Este filtro se ejecutará en cada petición. Su trabajo es:
 * Revisar si la petición tiene una cabecera Authorization con un token Bearer.
 * Validar ese token.
 * Si es válido, obtener los detalles del usuario y establecerlo en el contexto de
 * seguridad de Spring.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Si el header no empieza con bearer
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //Extraemos el token (quitamos "Bearer ")
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);

        //Si tenemos el email y el usuario no está ya autenticado en el contexto de seguridad
        if(userEmail != null && SecurityContextHolder.getContext() == null) {
            //Cargamos los detalles del usuario desde la bbdd
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            //Si el token es valido
            if(jwtService.isTokenValid(jwt, userDetails)){
                //Creamos un objeto de autenticacion
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null, // no necesitamos la contraseña
                    userDetails.getAuthorities() // los roles del usuario
                );
                authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );
                //Establecemos la  autenticacion en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // Continuamos con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}
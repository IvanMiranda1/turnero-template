package com.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.security.JwtAuthenticationFilter;
import com.app.security.OAuth2LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;

    
    public SecurityConfig(OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler,AuthenticationProvider authenticationProvider,JwtAuthenticationFilter jwtAuthF) {
        this.oAuth2LoginSuccessHandler = oAuth2LoginSuccessHandler;
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthFilter = jwtAuthF;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para simplificar (no recomendado para producción)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//no crea sesiones
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login/**", "/oauth2/**", "/public/**").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                .successHandler(oAuth2LoginSuccessHandler)
            )
            .authenticationProvider(authenticationProvider)// Registrar nuestro proovedor de authentication
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)// Añadir nuestro filtro JWT
            .logout(logout -> logout
                .logoutSuccessUrl("/")
            );
            return http.build();
    }
}

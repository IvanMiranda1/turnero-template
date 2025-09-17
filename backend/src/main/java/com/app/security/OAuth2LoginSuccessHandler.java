package com.app.security;

import com.app.usuario.Usuario;
import com.app.usuario.UsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    public OAuth2LoginSuccessHandler(UsuarioRepository usuarioRepository, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oauthUser.getAttributes();
        String email = attributes.get("email").toString();
        
        // Buscar o crear el usuario en la base de datos
        Usuario usuario = usuarioRepository.findByEmail(email)
        .orElseGet(() -> {
            Usuario newUser = new Usuario();
            newUser.setEmail(email);
            newUser.setNombre(attributes.get("name").toString());
            //newUser.setFotoUrl(attributes.get("picture") != null ? attributes.get("picture").toString() : null);e
            newUser.setProveedor(Usuario.Proveedor.GOOGLE);
            return usuarioRepository.save(newUser);
        });

        // Crear UserDetails para generar el token
        // Spring Security crea un UserDetails simple para nosotros con el email como username
        // y una lista varia de authorities si no se especifican roles.
        var userDetails = org.springframework.security.core.userdetails.User
                .withUsername(usuario.getEmail())
                .password("") // No necesitamos la contraseña para OAuth2
                .authorities("USER") // Puedes asignar roles según tu lógica
                .build();
        
        //Generar el token JWT
        String jwtToken = jwtService.generateToken(userDetails);

        // Redirigir al frontend con el token
        String redirectUrl = "http://localhost:4200/login-success?token=" + jwtToken;
        response.sendRedirect(redirectUrl);
    }
}

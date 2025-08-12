package com.app.infrastructure.security.model;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordHash {
    private final String hash;

    public PasswordHash(String hash) {
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    // Crea un hash a partir de un password plano
    public static PasswordHash fromPlainPassword(String rawPassword) {
        String hashed = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
        return new PasswordHash(hashed);
    }

    // Compara un password plano con el hash almacenado
    public boolean matches(String rawPassword) {
        return BCrypt.checkpw(rawPassword, hash);
    }
}

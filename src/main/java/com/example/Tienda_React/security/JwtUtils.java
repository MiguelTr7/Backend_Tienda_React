package com.example.Tienda_React.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    // 👇 Se ajustan los nombres de las propiedades al application.properties
    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    // 🔹 Genera el token JWT al iniciar sesión
    public String generateJwtToken(Authentication authentication) {
        CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername()) // el email del usuario
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔹 Construye la clave secreta a partir del valor Base64 del application.properties
    private Key key() {
        if (jwtSecret == null || jwtSecret.isEmpty()) {
            throw new IllegalStateException("❌ Error: 'app.jwtSecret' no está configurado en application.properties");
        }

        try {
            return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        } catch (Exception e) {
            throw new IllegalStateException("❌ Error al decodificar 'app.jwtSecret'. Asegúrate de que esté en Base64 válido.", e);
        }
    }

    // 🔹 Extrae el email (username) del token
    public String getEmailFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // 🔹 Valida que el token JWT sea correcto y no haya expirado
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            System.out.println("⚠️ Token JWT inválido: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("⚠️ Token JWT expirado: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("⚠️ Token JWT no soportado: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("⚠️ Claims JWT vacíos: " + e.getMessage());
        }
        return false;
    }
}

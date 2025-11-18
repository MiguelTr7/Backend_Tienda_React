package com.example.Tienda_React.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    // Clave secreta fija (de al menos 32 caracteres)
    private final String jwtSecret = "MiClaveSuperSeguraCon32Caracteres123!";

    // Tiempo de expiración en milisegundos (1 día)
    private final int jwtExpirationMs = 86400000;

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

    // 🔹 Clave secreta directa (sin Base64)
    private Key key() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
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

    // 🔹 Valida el token JWT
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

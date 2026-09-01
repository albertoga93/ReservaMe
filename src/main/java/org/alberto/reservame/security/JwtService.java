package org.alberto.reservame.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.alberto.reservame.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {


    private final String SECRET_KEY;

    public JwtService(@Value("${jwt.key}") String SECRET_KEY) {
        this.SECRET_KEY = SECRET_KEY;
    }

    //Convertirr el String de la key en un objeto SecretKey
    private SecretKey getSigninKey(){
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generarToken(Usuario usuario){
        Long ahora = System.currentTimeMillis();
        Long expiracion = ahora + (1000 * 60 * 60 * 8);

        return Jwts.builder()
                .subject(usuario.getEmail())
                .claim("rol", usuario.getRol().name())
                .claim("id", usuario.getId())
                .issuedAt(new Date(ahora))
                .expiration(new Date(expiracion))
                .signWith(getSigninKey())
                .compact();
    }

    private Claims extraerClaims (String token){
        return Jwts.parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extraerEmail(String token){
        return extraerClaims(token).getSubject();
    }

    private boolean esTokenExpirado(String token){
        return extraerClaims(token).getExpiration().before(new Date());
    }

    public boolean esTokenValido(String token, UserDetails userDetails){
        final String email = extraerEmail(token);
        return email.equals(userDetails.getUsername()) && !esTokenExpirado(token);
    }
}

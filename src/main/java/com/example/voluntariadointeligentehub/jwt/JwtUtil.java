package com.example.voluntariadointeligentehub.jwt;

import com.example.voluntariadointeligentehub.entities.Admin;
import com.example.voluntariadointeligentehub.entities.Instituicao;
import com.example.voluntariadointeligentehub.entities.Voluntario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // Chave secreta forte com pelo menos 64 caracteres base64 (512 bits)
    private static final String SECRET_KEY_BASE64 = "V/6mVK63wNiZwyzY+/bmzf+QgSZtMGyYsj3KxEB0j47lgjWcgpPIsmWOZLmdauA4SYZS4YlO/8qhmYKK6TKGvQ==";

    private final SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY_BASE64));
    private final long expirationMillis = 86400000; // 24 horas

    // === VOLUNTÁRIO ===
    public String gerarTokenVoluntario(Voluntario voluntario) {
        return Jwts.builder()
                .setSubject(voluntario.getEmailInstitucional())
                .claim("id", voluntario.getId())
                .claim("tipo", "voluntario")
                .claim("nome", voluntario.getNome())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    // === INSTITUIÇÃO ===
    public String gerarTokenInstituicao(Instituicao instituicao) {
        return Jwts.builder()
                .setSubject(instituicao.getEmail())
                .claim("id", instituicao.getId())
                .claim("tipo", "instituicao")
                .claim("nome", instituicao.getNome())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    // === ADMIN ===
    public String gerarToken(Admin admin) {
        return Jwts.builder()
                .setSubject(admin.getEmail())
                .claim("id", admin.getId())
                .claim("tipo", "ADMIN") // Por quê: distingue no filtro e padroniza autoridade
                .claim("nome", admin.getNome())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public String gerarToken(Voluntario voluntario) {
        return gerarTokenVoluntario(voluntario);
    }

    public String gerarToken(Instituicao instituicao) {
        return gerarTokenInstituicao(instituicao);
    }

    public String getEmailFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            System.out.println("Erro ao validar token: " + e.getMessage());
            throw e;
        }
    }


    public boolean isTokenValido(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getTipoFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("tipo", String.class);
    }
}

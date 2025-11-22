package org.agentofcode.the_last_stand_backend.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

//    public JwtService(){}

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("GameBackend")
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // maybe increase to 5 hours?
                .setIssuedAt(new Date())
                .signWith(secretKey)
                .compact();
    }

    public String validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }



    public String extractUserId(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }


    //    private static final String SECRET = "replace-with-a-real-secret-key";

//    public String generateToken(String userId) {
//        return Jwts.builder()
//                .setSubject(userId)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
//                .signWith(SignatureAlgorithm.HS256, SECRET)
//                .compact();
//    }
}

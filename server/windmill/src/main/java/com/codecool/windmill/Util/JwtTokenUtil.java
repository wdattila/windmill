package com.codecool.windmill.Util;

import com.codecool.windmill.Model.User;
import com.codecool.windmill.Repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {
    @Autowired
    private UserRepository userRepository;

    private final static long TOKEN_VALIDITY = 5 * 60 * 60;
    private final String secret = "secret";

    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsConverter){
        Claims claims = getAllClaimsFromToken(token);
        return claimsConverter.apply(claims);
    }

    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    private String buildToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS512, secret)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .compact();
    }

    public String generateToken(String user){
        Map<String, Object> claims = new HashMap<>();
        return buildToken(claims, user);
    }
}

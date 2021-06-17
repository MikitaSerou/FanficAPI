package com.example.fanficapi.jwt;

import com.example.fanficapi.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtUtils {

    @Value("${jwtSecret}")
    private String jwtSecret;

    @Value("${jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateToken(@AuthenticationPrincipal UserDetailsImpl userPrincipal, //TODO check this
                                Authentication authentication){
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }
        catch(SignatureException signatureException){
            log.error("Invalid JWT signature: {}",  signatureException.getMessage());
        }
        catch (MalformedJwtException malformedJwtException){
            log.error("Invalid JWT token: {}", malformedJwtException.getMessage());
        }
        catch (ExpiredJwtException expiredJwtException){
            log.error("JWT token is expired: {}", expiredJwtException.getMessage());
        }
        catch (UnsupportedJwtException unsupportedJwtException){
            log.error("JWT token is unsupported: {}", unsupportedJwtException.getMessage());
        }
        catch (IllegalArgumentException illegalArgumentException){
            log.error("JWT claims string is empty: {}", illegalArgumentException.getMessage());
        }
        return false;
    }
}

package com.auth.jwt.security;

import com.auth.jwt.DTO.RequestDTO;
import com.auth.jwt.entity.AuthUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.Map;


@Component
@Slf4j
public class JwtProvider {
    @Value("${jwt.secret}")
    private String SECRET;

    @Autowired
    private RouteValidator routeValidator;

    @PostConstruct
    protected void init() {
        SECRET = Base64.getEncoder().encodeToString(SECRET.getBytes());
    }

    public String createToken(AuthUser authUser) {
        Map<String, Object> claims = Jwts.claims().setSubject(authUser.getUsername());
        claims.put("id", authUser.getId());
        claims.put("role", authUser.getRole());
        Date now = new Date();
        Date exp = new Date(now.getTime() + 3600000);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public boolean validate(String token, RequestDTO requestDTO) {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token);
        } catch (Exception exception) {
            return false;
        }
        if(!isAdmin(token) && routeValidator.isAdmin(requestDTO)) {
            return false;
        }
        return true;
    }

    public String getUsernameFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody().getSubject();
        } catch (Exception exception) {
            return "Bad Token";
        }
    }

    private boolean isAdmin(String token) {
        return  Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token).getBody()
                    .get("role").equals("admin");
    }
}

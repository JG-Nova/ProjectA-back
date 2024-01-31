package org.jgprojects.a.web.config;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JwtUtil {
    private final Algorithm algorithm;

    private final Config config;

    public JwtUtil(Config config){
        this.config = config;
        this.algorithm = Algorithm.HMAC256(this.config.getJwtSecret());
    }
    public String create(String sub){
        return JWT.create()
        .withSubject(sub)
        .withIssuer(this.config.getJwtIssuer())
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis()+TimeUnit.DAYS.toMillis(15)))
        .sign(algorithm);
    }
    public boolean isValid(String jwt){
        try {
            JWT.require(algorithm)
            .build()
            .verify(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public String getSub(String jwt){
        return JWT.require(algorithm)
        .build()
        .verify(jwt)
        .getSubject();
    }
}

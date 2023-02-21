package br.com.confchat.api.utils;

import java.sql.Date;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.confchat.api.models.User;
@Component
public class JwtUtils {
    public static final int TOKEN_EXPIRATION = 3600_000;
    public static final String TOKEN_PASSWORD = "7083d396-aa13-4bd2-b4d6-15370c501e8b";
    public static final String ATTRIBUTE_PREFIX = "Bearer ";
    public static String generateJwt(User user){
        long now = System.currentTimeMillis();
        String token = JWT.create()
                .withSubject(user.getName())
                .withIssuer(String.valueOf(user.getId()))
                .withIssuedAt(new Date(now))
                .withExpiresAt(new Date(now + TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(TOKEN_PASSWORD));
        return token;
    } 
    public static DecodedJWT verify(String authorizer){
        try{
            String token = authorizer.replace(ATTRIBUTE_PREFIX, "");
            var verify = JWT.require(Algorithm.HMAC512(JwtUtils.TOKEN_PASSWORD)).build().verify(token);
            var dateNow = new Date(System.currentTimeMillis());
            var dateExpiresAt = verify.getExpiresAt().getTime();
            if(dateNow.getTime() > dateExpiresAt)
                return null;
            return verify;
        }catch(Exception e){
            return null;
        }
    }
}

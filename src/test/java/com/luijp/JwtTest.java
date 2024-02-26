package com.luijp;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGen(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", "1");
        claims.put("name", "张三");
        String token = JWT.create()
                .withClaim("user", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .sign(Algorithm.HMAC256("123456"));

        System.out.println(token);

    }

    @Test
    public void testParse(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJ1c2VyIjp7Im5hbWUiOiLlvKDkuIkiLCJpZCI6IjEifSwiZXhwIjoxNzA5MjE1ODgwfQ" +
                ".uJKYBA0r9E2Negba19KTpkC2nd_8_QA4KJqiiqDzlgs";
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("123456"))
                .build()
                .verify(token);
        System.out.println(decodedJWT.getClaim("user").asMap());
    }
}

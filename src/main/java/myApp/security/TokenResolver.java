package myApp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import myApp.models.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenResolver {
    private static final String SECRET = "secret";

    public static String createToken(User user) {
        Algorithm algorithmHS = Algorithm.HMAC256(SECRET);
        Map<String, Object> claims = new HashMap<>();
        claims.put("alg", "HS256");
        Date now = new Date();
        return JWT.create()
                .withExpiresAt(new Date(now.getTime() + 10 * 60000)) //10 min
                .withHeader(claims)
                .withClaim("sub", user.getId())
                .withClaim("nick", user.getNickname())
                .withClaim("role", user.getRole().getUserRole())
                .sign(algorithmHS);
    }

    public static DecodedJWT verify(String token) {
        DecodedJWT jwt;
        try {
            Algorithm algorithmHS = Algorithm.HMAC256(SECRET);
            JWTVerifier jwtVerifier = JWT.require(algorithmHS)
                    .build();
            jwt = jwtVerifier.verify(token);

        } catch (JWTVerificationException e) {
            throw new IllegalStateException(e);
        }
        return jwt;
    }

}

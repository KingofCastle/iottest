package com.xinguang.iot.common.utils;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xinguang.iot.common.config.Global;

public class JwtUtils {

    private static Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private static final String secret = Global.getConfig("jwt_secret");
    private static final String issuer = Global.getConfig("jwt_issuer");
    private static final String audience = Global.getConfig("jwt_audience");

    private static final String TOKEN = "token";

    public static void main(String[] args) {
        String token = signToken("123456");
//        String token = sign(TOKEN, "123456", 1);
//        logger.info(token);
////        token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJpc3MiOiJhdXRoMCJ9.AbIJTDMFc7yUa5MhvcP03nJPyCPzZtQcGEp-zWfOkEE";
//
        try {
            String userid = verify(token);
            logger.info(userid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String sign(String key, String value, int expirySeconds) {
        String token = null;
        try {
            Date date = new Date(System.currentTimeMillis() + expirySeconds*1000);
            token = JWT.create().withClaim(key, value)
                    .withIssuer(issuer)
                    .withAudience(audience)
                    .withExpiresAt(date)
                    .sign(Algorithm.HMAC256(secret));
        } catch (Exception exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            exception.printStackTrace();
        }

        return token;
    }
    
    /**
     * jwt加密token 过期时间1天
     * @param userId 
     */
    public static String signToken(String userId){
    	return sign(TOKEN, userId,60*60*24);
    }

    /**
     * jwt加密refreshToken 过期时间30天
     * @param userId
     */
    public static String signRefreshToken(String userId){
    	return sign(TOKEN, userId,60*60*24*30);
    }
    
    /**
     * jwt解密token
     * @param token
     */
    public static String verify(String token) throws Exception {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer(issuer)
                .withAudience(audience)
                .build(); //Reusable verifier instance
        DecodedJWT jwt = verifier.verify(token);

        return jwt.getClaim(TOKEN).asString();
    }
    
}

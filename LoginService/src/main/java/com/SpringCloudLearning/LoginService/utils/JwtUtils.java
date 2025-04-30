/*package com.SpringCloudLearning.LoginService.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static String signKey = "ytz2005";
    private static Long expire = 43200000L;

    public static String genJwt(Map<String, Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims).signWith(SignatureAlgorithm.HS256, signKey)
                .setExpiration(new Date(System.currentTimeMillis()+expire))
                .compact();
        return jwt;
    }

    public static Claims parseJwt(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
*/
package com.SpringCloudLearning.LoginService.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static final String signKey = "ytz2005ytz2005ytz2005ytz2005ytz2005ytz2005ytz2005ytz2005"; // 密钥长度必须 ≥32 字符
    private static final SecretKey key = Keys.hmacShaKeyFor(signKey.getBytes());
    private static final Long expire = 43200000L;

    public static String genJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .claims(claims) // 替换旧版 addClaims()
                .expiration(new Date(System.currentTimeMillis() + expire))
                .signWith(key) // 新签名方式
                .compact();
    }

    public static Claims parseJwt(String jwt) {
        return Jwts.parser()
                .verifyWith(key) // 替换旧版 setSigningKey()
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}
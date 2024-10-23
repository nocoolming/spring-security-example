package org.ming.example.spring.security.jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);
    // 使用 Keys.secretKeyFor 生成安全密钥
    private final SecretKey SECRET_KEY =
            Keys.secretKeyFor(SignatureAlgorithm.HS512);  // 符合HS512算法要求的密钥

    private final long EXPIRATION_TIME = 86400000;  // JWT 有效期（1天）

    // 生成 JWT
    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        log.info("Secret key: {}", Base64.getEncoder().encodeToString(SECRET_KEY.getEncoded()));

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SECRET_KEY)  // 使用自动生成的密钥
                .compact();
    }

    // 从 JWT 中提取用户名
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // 验证 JWT 是否有效
    public boolean validateToken(String token) {
        try {
            Jws<Claims> jwts =   Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);

            Date expiration =jwts.getBody().getExpiration();

            log.info("Expiration: {}, now: {}", expiration.toString(), new Date().toString());
            if(expiration.getTime() <= (new Date()).getTime()){
                return false;
            }

            log.info(expiration.toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

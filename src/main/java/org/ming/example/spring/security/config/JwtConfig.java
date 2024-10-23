package org.ming.example.spring.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
//@PropertySource("classpath:application.yml")
//@Component
public class JwtConfig {
    @Value("${app.secretKey}")
    private String secretKey;

    @Value("${app.expirationMilliseconds}")
    private Long expirationMilliseconds;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Long getExpirationMilliseconds() {
        return expirationMilliseconds;
    }

    public void setExpirationMilliseconds(Long expirationMilliseconds) {
        this.expirationMilliseconds = expirationMilliseconds;
    }
}

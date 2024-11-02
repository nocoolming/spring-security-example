package org.ming.example.spring.security.config;

import org.ming.example.spring.security.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private PermissionConfig permissionConfig;


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                            auth
                                    .requestMatchers("/signIn", "signOn", "forgetPassword").permitAll();


                            auth.requestMatchers("/admin").hasRole ("ADMIN");
                            auth.requestMatchers("/user").hasRole ("USER");

                            auth// 允许 /api/auth/** 请求不需要认证
                                    .anyRequest().authenticated();  // 其他请求需要认证


                        }
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);  // 添加 JWT 过滤器


        // 动态加载权限规则
//        for (PermissionConfig.PermissionRule rule : permissionConfig.getRules()) {
//            String[] roles = rule.getRoles().toArray(new String[0]);
////            http.authorizeRequests().antMatchers(rule.getPath()).hasAnyRole(roles);
//        }

        return http.build();
    }
}

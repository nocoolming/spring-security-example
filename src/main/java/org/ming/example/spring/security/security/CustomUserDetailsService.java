package org.ming.example.spring.security.security;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
public class CustomUserDetailsService implements UserDetailsService {

    // 假设你从数据库加载用户
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return switch (username) {
            case "admin" -> org.springframework.security.core.userdetails.User
                    .withUsername(username)
                    .password("{noop}password")  // 密码应使用加密方式处理
                    .roles("ADMIN")
                    .build();
            case "user" -> org.springframework.security.core.userdetails.User
                    .withUsername(username)
                    .password("{noop}password")  // 密码应使用加密方式处理
                    .roles("USER")
                    .build();
            default -> throw new UsernameNotFoundException("User not found");
        };
        // 这里应该从数据库加载用户信息
//        if ("admin".equals(username)) {
//            return org.springframework.security.core.userdetails.User
//                    .withUsername(username)
//                    .password("{noop}password")  // 密码应使用加密方式处理
//                    .roles("ADMIN")
//                    .build();
//        } else {
//            throw new UsernameNotFoundException("User not found");
//        }
    }
}

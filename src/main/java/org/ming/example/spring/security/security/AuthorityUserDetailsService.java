package org.ming.example.spring.security.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthorityUserDetailsService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(AuthorityUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Set<GrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        authorities.add(new SimpleGrantedAuthority("User"));
        authorities.add(new SimpleGrantedAuthority("Guest"));

//        return new org.springframework.security.core.userdetails.User("admin", "{noop}password", authorities);
        return
                org.springframework.security.core.userdetails.User
                        .withUsername(username)
                        .password("{noop}password")
                        .roles("ADMIN")
                        .authorities(authorities)
                        .build();
    }

}

package com.article.service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Value("${admin.credentials.username}")
    private String admin;

    @Value("${admin.credentials.password}")
    private String adminPassword;

    @Override
    public UserDetails loadUserByUsername(String username) {
        if ("admin".equals(username)) {
            return new User(admin, adminPassword,
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}

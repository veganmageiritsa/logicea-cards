package com.nl.logiceacards.infrastructure.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.nl.logiceacards.infrastructure.db.users.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class UserDetailsConfiguration {
    private final AppUserRepository userRepository;
    
    
    @Bean
    @Transactional
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                                         .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

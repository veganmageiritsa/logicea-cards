package com.nl.logiceacards.application.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nl.logiceacards.infrastructure.db.users.entity.AppUserEntity;
import com.nl.logiceacards.infrastructure.db.users.repository.AppUserRepository;
import com.nl.logiceacards.infrastructure.web.requests.AppLoginRequest;

@Service
public class AuthenticationService {
    
    private final AppUserRepository userRepository;
    
    private final AuthenticationManager authenticationManager;
    
    public AuthenticationService(
        AppUserRepository userRepository,
        AuthenticationManager authenticationManager
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }
    
    
    @Transactional
    public AppUserEntity authenticate(AppLoginRequest input) {
        authenticateAndSetContext(input);
        
        return userRepository.findByUsername(input.getUsername())
                             .orElseThrow();
    }
    
    private void authenticateAndSetContext(final AppLoginRequest input) {
        var authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                input.getUsername(),
                input.getPassword()
            )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    
}
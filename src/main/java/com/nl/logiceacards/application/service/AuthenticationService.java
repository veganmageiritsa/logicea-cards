package com.nl.logiceacards.application.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.nl.logiceacards.domain.model.user.AppUser;
import com.nl.logiceacards.infrastructure.db.users.entity.AppUserEntity;
import com.nl.logiceacards.infrastructure.db.users.repository.AppUserRepository;
import com.nl.logiceacards.infrastructure.web.requests.AppLoginRequest;

@Service
public class AuthenticationService {
    private final AppUserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;
    
    public AuthenticationService(
        AppUserRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
//    public AppUser signup(RegisterUserDto input) {
//        User user = new User()
//            .setFullName(input.getFullName())
//            .setEmail(input.getEmail())
//            .setPassword(passwordEncoder.encode(input.getPassword()));
//
//        return userRepository.save(user);
//    }
    
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
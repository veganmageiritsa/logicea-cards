package com.nl.logiceacards.application.service;

import org.springframework.stereotype.Service;

import com.nl.logiceacards.application.port.in.AppAuthenticationUseCase;
import com.nl.logiceacards.domain.model.user.AppLogin;
import com.nl.logiceacards.infrastructure.configuration.security.JwtService;
import com.nl.logiceacards.infrastructure.web.requests.AppLoginRequest;

@Service
public class AppAuthenticationService implements AppAuthenticationUseCase {
    
    private final AuthenticationService authenticationService;
    
    private final JwtService jwtService;
    
    public AppAuthenticationService(final  AuthenticationService authenticationService, final JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }
    
    @Override
    public AppLogin login(AppLoginRequest request) {
        
        var authenticatedUser = authenticationService.authenticate(request);
        
        var jwtToken = jwtService.generateToken(authenticatedUser);
        
        return new AppLogin(jwtToken,
                            jwtService.getExpirationTime()
                            );
    }
    
}

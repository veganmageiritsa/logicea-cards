package com.nl.logiceacards.infrastructure.configuration.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.nl.logiceacards.infrastructure.db.users.entity.AppUserEntity;

@Component
public class ApiTokenAuthFacade {
    
    public AppUserEntity getAuthenticatedUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            return (AppUserEntity) authentication.getPrincipal();
        }
        else {
            throw new AuthenticationCredentialsNotFoundException("User not authenticated");
        }
    }
    
}

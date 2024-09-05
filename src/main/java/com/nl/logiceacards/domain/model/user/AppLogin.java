package com.nl.logiceacards.domain.model.user;

import java.util.List;

public record AppLogin( String token,
                        String username,
                        String email,
                        String firstName,
                        String lastName,
                        long expirationTime,
                        List<String> roles){
    
    public AppLogin(final String jwtToken, final long expirationTime) {
        this(jwtToken, null, null, null, null,  expirationTime, null);
    }
    
}


package com.nl.logiceacards.application.port.in;


import com.nl.logiceacards.domain.model.user.AppLogin;
import com.nl.logiceacards.infrastructure.web.requests.AppLoginRequest;

public interface AppAuthenticationUseCase {
    
    AppLogin login(AppLoginRequest request);
    
}

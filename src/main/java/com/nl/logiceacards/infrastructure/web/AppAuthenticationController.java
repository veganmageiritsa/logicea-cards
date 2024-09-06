package com.nl.logiceacards.infrastructure.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nl.logiceacards.application.port.in.AppAuthenticationUseCase;
import com.nl.logiceacards.domain.model.user.AppLogin;
import com.nl.logiceacards.infrastructure.web.requests.AppLoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/app/auth")
@RequiredArgsConstructor
public class AppAuthenticationController {
    
    private final AppAuthenticationUseCase appAuthenticationUseCase;
    
    @PostMapping("/login")
    public ResponseEntity<AppLogin> authenticateUser(@Valid @RequestBody AppLoginRequest appLoginRequest) {
        return ResponseEntity.ok(
            appAuthenticationUseCase.login(appLoginRequest)
        );
    }
    
}

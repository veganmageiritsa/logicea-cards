package com.nl.logiceacards.infrastructure.web.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppLoginRequest {
    
    @NotBlank
    private String username;
    
    @NotBlank
    private String password;
    
}
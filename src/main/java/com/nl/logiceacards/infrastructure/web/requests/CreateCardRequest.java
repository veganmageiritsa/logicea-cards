package com.nl.logiceacards.infrastructure.web.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class CreateCardRequest {
    
    @NotBlank
    private String name;
    
    @Pattern(regexp = "^#[0-9a-zA-Z]{5}$")
    private String color;
    
    private String description;
    
   
    
}

package com.nl.logiceacards.domain.model.user;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import com.nl.logiceacards.infrastructure.db.users.entity.AppRoleEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppUser implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 4222007460000125927L;
    
    private Long id;
    
    private String username;
    
    private String email;
    
    private String password;
    
    private String firstName;
    
    private String lastName;
    
    private Set<AppRoleEntity> roles;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    
}
    

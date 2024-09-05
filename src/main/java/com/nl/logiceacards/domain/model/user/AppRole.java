package com.nl.logiceacards.domain.model.user;

import java.io.Serial;
import java.io.Serializable;

import com.nl.logiceacards.infrastructure.db.users.entity.AppRoleType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppRole implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 4011007461111125927L;
    
    private Integer id;
    
    private AppRoleType name;
    
}

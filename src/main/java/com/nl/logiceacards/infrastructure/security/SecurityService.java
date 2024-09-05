package com.nl.logiceacards.infrastructure.security;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nl.logiceacards.domain.model.card.Card;
import com.nl.logiceacards.infrastructure.db.cards.entity.CardEntity;
import com.nl.logiceacards.infrastructure.db.exception.ResourceNotFoundException;
import com.nl.logiceacards.infrastructure.db.users.entity.AppUserEntity;

@Service
public class SecurityService {
    
    
    public boolean isResourceOwner(List<CardEntity> cards) {
        var principal = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return cards
            .stream()
            .allMatch(card -> card.getAppUserEntity().getUsername().equals(principal.getUsername()));
    }
    
    public boolean isResourceOwner(Optional<CardEntity> card) {
        var principal = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return card.isPresent() && card.get().getAppUserEntity().getUsername().equals(principal.getUsername());
        
    }
    
}

package com.nl.logiceacards.application.port.in;

import org.springframework.data.domain.Page;

import com.nl.logiceacards.infrastructure.web.responses.CardResponse;

public interface FindCardsUseCase {
    
    
    Page<CardResponse> findAll(int pageNo, int pageSize, String sortBy, String sortDirection);
    
    
}

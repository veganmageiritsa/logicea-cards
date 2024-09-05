package com.nl.logiceacards.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nl.logiceacards.application.port.in.FindCardsUseCase;
import com.nl.logiceacards.application.port.out.CardsRepositoryPort;
import com.nl.logiceacards.infrastructure.db.cards.repository.adapter.CardsRepositoryAdapter;
import com.nl.logiceacards.infrastructure.web.responses.CardResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindCardsService implements FindCardsUseCase {
    
    private final CardsRepositoryPort cardsRepositoryPort;
    @Override
    public Page<CardResponse> findAll(final int pageNo, final int pageSize, final String sortBy, final String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return cardsRepositoryPort.findAll(pageable);
    }
    
}

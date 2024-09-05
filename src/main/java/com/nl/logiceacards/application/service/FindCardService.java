package com.nl.logiceacards.application.service;

import org.springframework.stereotype.Service;

import com.nl.logiceacards.application.port.in.FindCardUseCase;
import com.nl.logiceacards.application.port.out.CardsRepositoryPort;
import com.nl.logiceacards.domain.model.card.Card;
import com.nl.logiceacards.domain.model.card.query.FindCardQuery;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindCardService implements FindCardUseCase {
private final CardsRepositoryPort cardsRepositoryPort;
    
    
    @Override
    public Card findCard(final FindCardQuery query) {
        return cardsRepositoryPort.findCard(query);
    }
    
}

package com.nl.logiceacards.application.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.nl.logiceacards.application.port.in.SearchCardsUseCase;
import com.nl.logiceacards.application.port.out.CardsRepositoryPort;
import com.nl.logiceacards.domain.model.card.Card;
import com.nl.logiceacards.domain.model.card.CardsFilter;
import com.nl.logiceacards.infrastructure.db.cards.entity.CardEntity;
import com.nl.logiceacards.infrastructure.db.cards.repository.specification.CardSpecification;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchCardsService implements SearchCardsUseCase {
    
    private final CardsRepositoryPort cardsRepositoryPort;
    
    @Override
    public List<Card> searchCards(final CardsFilter filter) {
        Specification<CardEntity> spec = CardSpecification.filterBy(filter);
        return cardsRepositoryPort.searchCards(spec);
    }
    
    
}

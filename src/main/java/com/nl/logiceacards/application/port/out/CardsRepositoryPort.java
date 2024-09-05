package com.nl.logiceacards.application.port.out;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.nl.logiceacards.domain.model.card.Card;
import com.nl.logiceacards.domain.model.card.command.DeleteCardCommand;
import com.nl.logiceacards.domain.model.card.command.UpdateCardCommand;
import com.nl.logiceacards.domain.model.card.query.FindCardQuery;
import com.nl.logiceacards.infrastructure.db.cards.entity.CardEntity;
import com.nl.logiceacards.infrastructure.web.responses.CardResponse;

public interface CardsRepositoryPort {
    
    Card createCard(Card command);
    
    Page<CardResponse> findAll(Pageable pageable);
    
    Card updateCard(UpdateCardCommand command);
    
    Card findCard(FindCardQuery findCardQuery);
    
    List<Card> searchCards(Specification<CardEntity> spec);
    
    void deleteCard(DeleteCardCommand deleteCardCommand);
    
}

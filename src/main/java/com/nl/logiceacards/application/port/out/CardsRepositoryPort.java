package com.nl.logiceacards.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.nl.logiceacards.domain.model.card.Card;
import com.nl.logiceacards.domain.model.card.command.DeleteCardCommand;
import com.nl.logiceacards.domain.model.card.command.UpdateCardCommand;
import com.nl.logiceacards.domain.model.card.query.FindCardQuery;
import com.nl.logiceacards.infrastructure.db.cards.entity.CardEntity;
import com.nl.logiceacards.infrastructure.web.responses.CardResponse;
import com.nl.logiceacards.infrastructure.web.responses.CardsPageResponse;

public interface CardsRepositoryPort {
    
    Card createCard(Card command);
    
    Page<CardResponse> findAll(Pageable pageable);
    
    Card updateCard(UpdateCardCommand command);
    
    Card findCard(FindCardQuery findCardQuery);
    
    CardsPageResponse<CardResponse> searchCards(Specification<CardEntity> spec, final PageRequest pageable);
    
    void deleteCard(DeleteCardCommand deleteCardCommand);
    
}

package com.nl.logiceacards.infrastructure.web.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nl.logiceacards.domain.model.card.Card;
import com.nl.logiceacards.domain.model.card.CardsFilter;
import com.nl.logiceacards.domain.model.card.command.CreateCardCommand;
import com.nl.logiceacards.domain.model.card.command.UpdateCardCommand;
import com.nl.logiceacards.domain.model.card.query.FindCardQuery;
import com.nl.logiceacards.infrastructure.db.cards.repository.criteria.CardSearchCriteria;
import com.nl.logiceacards.infrastructure.web.requests.CreateCardRequest;
import com.nl.logiceacards.infrastructure.web.requests.UpdateCardRequest;
import com.nl.logiceacards.infrastructure.web.responses.CardResponse;
import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
    componentModel = SPRING,
    injectionStrategy = CONSTRUCTOR
)
public interface CardMapper {
     CreateCardCommand toCommand(CreateCardRequest dto);
    
     CardResponse toDto(Card card);
    
     UpdateCardCommand toCommand(UpdateCardRequest dto, int cardId);
    
    @Mapping(target = "id", source = "cardId")
     FindCardQuery toQuery(final Integer cardId) ;
    
    @Mapping(target = "userId", ignore = true)
     CardsFilter toFilter(final CardSearchCriteria criteria);
    
     List<CardResponse> toDtos(final List<Card> cards);
}

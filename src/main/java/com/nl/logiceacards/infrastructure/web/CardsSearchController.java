package com.nl.logiceacards.infrastructure.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.nl.logiceacards.application.port.in.SearchCardsUseCase;

import com.nl.logiceacards.domain.model.card.Card;
import com.nl.logiceacards.domain.model.card.CardsFilter;
import com.nl.logiceacards.infrastructure.db.cards.repository.criteria.CardSearchCriteria;
import com.nl.logiceacards.infrastructure.web.responses.CardResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/app/search")
@RequiredArgsConstructor
public class CardsSearchController {
    
    private final SearchCardsUseCase searchCardsUseCase;
    
    @GetMapping
    public ResponseEntity<List<CardResponse>> searchCards(
        CardSearchCriteria criteria) {
        return new ResponseEntity<>(
            CardSearchControllerMapper.INSTANCE.toDtos(
                searchCardsUseCase.searchCards(CardSearchControllerMapper.INSTANCE.toFilter(criteria))
            ),
            HttpStatus.OK
        );
    }
    
    @Mapper
    abstract static class CardSearchControllerMapper {
        
        public static CardSearchControllerMapper INSTANCE = Mappers.getMapper(CardSearchControllerMapper.class);
        
        abstract CardsFilter toFilter(final CardSearchCriteria criteria);
        
        abstract List<CardResponse> toDtos(final List<Card> cards);
        
        abstract CardResponse toDto(final Card card);
        
    }
    
}

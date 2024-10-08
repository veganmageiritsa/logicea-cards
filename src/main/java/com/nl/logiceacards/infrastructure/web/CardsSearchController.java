package com.nl.logiceacards.infrastructure.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nl.logiceacards.application.port.in.SearchCardsUseCase;
import com.nl.logiceacards.infrastructure.aspect.LogExecutionTime;
import com.nl.logiceacards.infrastructure.db.cards.repository.criteria.CardSearchCriteria;
import com.nl.logiceacards.infrastructure.web.mappers.CardMapper;
import com.nl.logiceacards.infrastructure.web.responses.CardResponse;
import com.nl.logiceacards.infrastructure.web.responses.CardsPageResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/app/search")
@RequiredArgsConstructor
public class CardsSearchController {
    
    private final SearchCardsUseCase searchCardsUseCase;
    
    private final CardMapper cardMapper;
    @GetMapping
    @LogExecutionTime(additionalMessage = "Search cards With Filters")
    public ResponseEntity<CardsPageResponse<CardResponse>> searchCards(
        CardSearchCriteria criteria) {
        return new ResponseEntity<>(searchCardsUseCase.searchCards(cardMapper.toFilter(criteria)), HttpStatus.OK);
    }
    
}

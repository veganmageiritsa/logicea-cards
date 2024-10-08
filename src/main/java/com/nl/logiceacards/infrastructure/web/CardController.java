package com.nl.logiceacards.infrastructure.web;


import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nl.logiceacards.application.port.in.CreateCardUseCase;
import com.nl.logiceacards.application.port.in.DeleteCardUseCase;
import com.nl.logiceacards.application.port.in.FindCardUseCase;
import com.nl.logiceacards.application.port.in.FindCardsUseCase;
import com.nl.logiceacards.application.port.in.UpdateCardUseCase;
import com.nl.logiceacards.domain.model.card.command.DeleteCardCommand;
import com.nl.logiceacards.infrastructure.aspect.LogExecutionTime;
import com.nl.logiceacards.infrastructure.web.mappers.CardMapper;
import com.nl.logiceacards.infrastructure.web.requests.CreateCardRequest;
import com.nl.logiceacards.infrastructure.web.requests.UpdateCardRequest;
import com.nl.logiceacards.infrastructure.web.responses.CardResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/app/cards")
@RequiredArgsConstructor
public class CardController {
    
    private final CreateCardUseCase createCardUseCase;
    
    private final UpdateCardUseCase updateCardUseCase;
    
    private final FindCardsUseCase findCardsUseCase;
    
    private final FindCardUseCase findCardUseCase;
    
    private final DeleteCardUseCase deleteCardUseCase;
    
    private final CardMapper cardMapper;
    
    @GetMapping("/{cardId}")
    @LogExecutionTime(additionalMessage = "Get Card")
    public ResponseEntity<CardResponse> findCard(
        @PathVariable(name = "cardId") @NotNull Integer cardId) {
        return new ResponseEntity<>(
            cardMapper.toDto(
                findCardUseCase.findCard(cardMapper.toQuery(cardId))
            ),
            HttpStatus.OK
        );
    }
    
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<CardResponse> createCard(@Valid @RequestBody CreateCardRequest createCardRequest) {
        return new ResponseEntity<>(
            cardMapper.toDto(
                createCardUseCase.createCard(cardMapper.toCommand(createCardRequest))
            ),
            HttpStatus.CREATED
        );
    }
    
    @PutMapping("/{cardId}")
    public ResponseEntity<CardResponse> updateCard(
        @PathVariable(name = "cardId") @NotNull Integer cardId,
        @Valid @RequestBody UpdateCardRequest updateCardRequest) {
        return new ResponseEntity<>(
            cardMapper.toDto(
                updateCardUseCase.updateCard(cardMapper.toCommand(updateCardRequest, cardId))
            ),
            HttpStatus.OK
        );
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @LogExecutionTime(additionalMessage = "Find All with Pagination and Sorting")
    public ResponseEntity<Page<CardResponse>> findCards(
        @RequestParam(defaultValue = "0") int pageNo,
        @RequestParam(defaultValue = "10") int pageSize,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "ASC") String sortDirection) {
        
        
        return ResponseEntity.ok(
            findCardsUseCase.findAll(pageNo, pageSize, sortBy, sortDirection));
    }
    
    @DeleteMapping("/{cardId}")
    public ResponseEntity<Void> deleteCard(
        @PathVariable(name = "cardId") @NotNull Integer cardId) {
        deleteCardUseCase.deleteCard(new DeleteCardCommand(cardId));
        return ResponseEntity.ok().build();
    }
    
}




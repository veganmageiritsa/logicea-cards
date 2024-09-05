package com.nl.logiceacards.application.service;

import org.springframework.stereotype.Service;

import com.nl.logiceacards.application.port.in.UpdateCardUseCase;
import com.nl.logiceacards.application.port.out.CardsRepositoryPort;
import com.nl.logiceacards.domain.model.card.Card;
import com.nl.logiceacards.domain.model.card.command.UpdateCardCommand;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateCardService implements UpdateCardUseCase {
    
    private final CardsRepositoryPort cardsRepositoryPort;
    @Override
    public Card updateCard(final UpdateCardCommand command) {
       return cardsRepositoryPort.updateCard(command);
       
    }
    
}

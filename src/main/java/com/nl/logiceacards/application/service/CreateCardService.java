package com.nl.logiceacards.application.service;

import org.springframework.stereotype.Service;

import com.nl.logiceacards.application.port.in.CreateCardUseCase;
import com.nl.logiceacards.application.port.out.CardsRepositoryPort;
import com.nl.logiceacards.domain.model.card.Card;
import com.nl.logiceacards.domain.model.card.command.CreateCardCommand;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CreateCardService implements CreateCardUseCase {
    
    private final CardsRepositoryPort cardsRepositoryPort;
    
    @Override
    public Card createCard(final CreateCardCommand command) {
        var card = Card.createCard(command);
        return cardsRepositoryPort.createCard(card);
    }
    
}

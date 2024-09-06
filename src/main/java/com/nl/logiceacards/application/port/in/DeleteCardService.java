package com.nl.logiceacards.application.port.in;

import org.springframework.stereotype.Service;

import com.nl.logiceacards.application.port.out.CardsRepositoryPort;
import com.nl.logiceacards.domain.model.card.command.DeleteCardCommand;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class DeleteCardService implements DeleteCardUseCase {
    
    private final CardsRepositoryPort cardsRepositoryPort;
    
    @Override
    public void deleteCard(final DeleteCardCommand deleteCardCommand) {
        cardsRepositoryPort.deleteCard(deleteCardCommand);
    }
    
}

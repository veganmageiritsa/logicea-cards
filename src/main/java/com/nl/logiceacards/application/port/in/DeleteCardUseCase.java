package com.nl.logiceacards.application.port.in;

import com.nl.logiceacards.domain.model.card.command.DeleteCardCommand;

public interface DeleteCardUseCase {
    
    void deleteCard(DeleteCardCommand deleteCardCommand);
    
}

package com.nl.logiceacards.application.port.in;

import com.nl.logiceacards.domain.model.card.Card;
import com.nl.logiceacards.domain.model.card.command.UpdateCardCommand;

public interface UpdateCardUseCase {
    
    Card updateCard(UpdateCardCommand command);
    
}

package com.nl.logiceacards.application.port.in;

import com.nl.logiceacards.domain.model.card.Card;
import com.nl.logiceacards.domain.model.card.command.CreateCardCommand;

public interface CreateCardUseCase {
    
    Card createCard(final CreateCardCommand command);}

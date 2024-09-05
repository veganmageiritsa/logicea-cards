package com.nl.logiceacards.application.port.in;

import com.nl.logiceacards.domain.model.card.Card;
import com.nl.logiceacards.domain.model.card.query.FindCardQuery;

public interface FindCardUseCase {
    
    Card findCard(FindCardQuery query);
    
}

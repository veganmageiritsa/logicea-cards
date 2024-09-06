package com.nl.logiceacards.application.port.in;

import com.nl.logiceacards.domain.model.card.CardsFilter;
import com.nl.logiceacards.infrastructure.web.responses.CardResponse;
import com.nl.logiceacards.infrastructure.web.responses.CardsPageResponse;

public interface SearchCardsUseCase {
    
    CardsPageResponse<CardResponse> searchCards(CardsFilter filter);
    
}

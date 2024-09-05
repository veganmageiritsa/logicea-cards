package com.nl.logiceacards.application.port.in;

import java.util.List;

import com.nl.logiceacards.domain.model.card.Card;
import com.nl.logiceacards.domain.model.card.CardsFilter;

public interface SearchCardsUseCase {
    
    List<Card> searchCards(CardsFilter filter);
    
}

package com.nl.logiceacards.domain.model.card.command;

import com.nl.logiceacards.domain.model.card.CardStatus;

public record UpdateCardCommand(int cardId, String name, String color, String description, CardStatus status) {

}

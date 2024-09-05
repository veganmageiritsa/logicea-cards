package com.nl.logiceacards.domain.model.card;

import com.nl.logiceacards.domain.model.card.command.CreateCardCommand;
import com.nl.logiceacards.infrastructure.db.cards.entity.CardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Card {
    private Integer id;
    
    private String name;
    
    private String color;
    
    private String description;
    
    private CardStatus status;
    
    private String userId;
    
    public static Card createCard(CreateCardCommand command){
        Card card = new Card();
        card.name = command.name();
        card.color = command.color();
        card.description = command.description();
        card.status = CardStatus.TO_DO;
        return card;
    }
    
    public static Card fromEntity(CardEntity cardEntity, final String email){
        Card card = new Card();
        card.id= cardEntity.getId();
        card.name = cardEntity.getName();
        card.color = cardEntity.getColor();
        card.description = cardEntity.getDescription();
        card.status = cardEntity.getStatus();
        card.userId = email;
        return card;
    }
    
}

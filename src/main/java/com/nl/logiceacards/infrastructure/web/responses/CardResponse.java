package com.nl.logiceacards.infrastructure.web.responses;

import com.nl.logiceacards.domain.model.card.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardResponse {
    
    private int id;
    
    private String name;
    
    private String color;
    
    private String description;
    
    private CardStatus status;
    
}

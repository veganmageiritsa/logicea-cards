package com.nl.logiceacards.infrastructure.db.cards.repository.criteria;

import java.util.Date;

import com.nl.logiceacards.domain.model.card.CardStatus;
import lombok.Data;

@Data
public class CardSearchCriteria {
    
    private String name;
    
    private String color;
    
    private String description;
    
    private CardStatus status;
    
    private Date createdAt;
    
    private Integer userId;
    
    private int pageNo;
    
    private int pageSize;
    
    private String sortBy;
    
    private String sortDirection;
    
}

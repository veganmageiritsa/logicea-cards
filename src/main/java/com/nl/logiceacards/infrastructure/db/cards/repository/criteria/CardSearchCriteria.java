package com.nl.logiceacards.infrastructure.db.cards.repository.criteria;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.nl.logiceacards.domain.model.card.CardStatus;
import lombok.Data;

@Data
public class CardSearchCriteria {
    
    private String name;
    
    private String color;
    
    private String description;
    
    private CardStatus status;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    
    private Integer userId;
    
    private Integer pageNo;
    
    private Integer pageSize;
    
    private String sortBy;
    
    private String sortDirection;
    
}

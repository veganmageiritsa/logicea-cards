package com.nl.logiceacards.infrastructure.web.requests;

import com.nl.logiceacards.domain.model.card.CardStatus;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Valid
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class UpdateCardRequest extends CreateCardRequest {
    
    CardStatus status;
    
}

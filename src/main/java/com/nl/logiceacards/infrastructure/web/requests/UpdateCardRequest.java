package com.nl.logiceacards.infrastructure.web.requests;

import com.nl.logiceacards.domain.model.card.CardStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
public class UpdateCardRequest extends CreateCardRequest{
   
    CardStatus status;
}

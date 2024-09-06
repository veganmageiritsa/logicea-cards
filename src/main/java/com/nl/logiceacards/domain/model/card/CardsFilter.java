package com.nl.logiceacards.domain.model.card;

import java.util.Date;

public record CardsFilter(String name, String color, String description, CardStatus status, Date createdAt,
                          Integer userId, Integer pageNo, Integer pageSize, String sortBy, String sortDirection) {

}

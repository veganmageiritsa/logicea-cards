package com.nl.logiceacards.infrastructure.db.cards.repository.specification;

import java.util.Date;

import org.springframework.data.jpa.domain.Specification;

import com.nl.logiceacards.domain.model.card.CardStatus;
import com.nl.logiceacards.domain.model.card.CardsFilter;
import com.nl.logiceacards.infrastructure.db.cards.entity.CardEntity;

public class CardSpecification {
    public static final String NAME = "name";
    public static final String COLOR = "color";
    public static final String DESCRIPTION = "description";
    public static final String STATUS = "status";
    public static final String CREATED_AT = "createdAt";
    public static final String APP_USER_ID = "appUserEntity";
    
    private CardSpecification() {
        //empty
    }
    
    public static Specification<CardEntity> filterBy(CardsFilter cardsFilter) {
        return Specification
            .where(hasName(cardsFilter.name()))
            .and(hasColor(cardsFilter.color()))
            .and(hasDescriptionLike(cardsFilter.description()))
            .and(hasStatus(cardsFilter.status()))
            .and(hasUserId(cardsFilter.userId()))
            .and(hasBeenCreatedAfter(cardsFilter.createdAt()));
    }
    
    private static Specification<CardEntity> hasName(String name) {
        return ((root, query, cb) -> name == null || name.isEmpty() ? cb.conjunction() : cb.equal(root.get(NAME), name));
    }
    
    public static Specification<CardEntity> hasUserId(Integer userId) {
        return ((root, query, cb) -> userId == null  ? cb.conjunction() : cb.equal(root.get(APP_USER_ID).get("id"), userId));
    }
    
    private static Specification<CardEntity> hasColor(String color) {
        return (root, query, cb) -> color == null ? cb.conjunction() : cb.equal(root.get(COLOR), color);
    }
    
    private static Specification<CardEntity> hasDescriptionLike(String description) {
        return (root, query, cb) -> description == null ? cb.conjunction() : cb.like(root.get(DESCRIPTION),"%" + description + "%");
    }
    
    private static Specification<CardEntity> hasStatus(CardStatus status) {
        return (root, query, cb) -> status == null ? cb.conjunction() : cb.equal(root.get(STATUS), status);
    }
    
    private static Specification<CardEntity> hasBeenCreatedAfter(Date createdAt) {
        return (root, query, cb) -> createdAt == null ? cb.conjunction() : cb.greaterThan(root.get(CREATED_AT), createdAt);
    }
}

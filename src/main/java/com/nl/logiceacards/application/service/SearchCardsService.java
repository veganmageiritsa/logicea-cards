package com.nl.logiceacards.application.service;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.nl.logiceacards.application.port.in.SearchCardsUseCase;
import com.nl.logiceacards.application.port.out.CardsRepositoryPort;
import com.nl.logiceacards.domain.model.card.CardsFilter;
import com.nl.logiceacards.infrastructure.db.cards.entity.CardEntity;
import com.nl.logiceacards.infrastructure.db.cards.repository.specification.CardSpecification;
import com.nl.logiceacards.infrastructure.web.responses.CardResponse;
import com.nl.logiceacards.infrastructure.web.responses.CardsPageResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchCardsService implements SearchCardsUseCase {
    
    private static final int PAGE_SIZE = 5;
    
    private static final int PAGE_NO = 0;
    private final CardsRepositoryPort cardsRepositoryPort;
    
    @Override
    public CardsPageResponse<CardResponse> searchCards(final CardsFilter filter) {
        Specification<CardEntity> spec = CardSpecification.filterBy(filter);
        var pageable = Optional.ofNullable(filter.pageNo())
                               .map(pageNo -> Optional.ofNullable(filter.sortBy())
                                                      .map(sortBy -> {
                                                          Sort sort = Sort.by(Sort.Direction.fromString(filter.sortDirection()), sortBy);
                                                          return PageRequest.of(pageNo, filter.pageSize(), sort);
                                                      }).orElse(PageRequest.of(pageNo, filter.pageSize())))
                               .orElse(PageRequest.of(0, PAGE_SIZE));
        
        return cardsRepositoryPort.searchCards(spec, pageable);
    }
    
    
}

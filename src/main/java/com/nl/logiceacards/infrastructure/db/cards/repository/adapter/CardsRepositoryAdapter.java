package com.nl.logiceacards.infrastructure.db.cards.repository.adapter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.nl.logiceacards.application.port.out.CardsRepositoryPort;
import com.nl.logiceacards.domain.model.card.Card;
import com.nl.logiceacards.domain.model.card.command.DeleteCardCommand;
import com.nl.logiceacards.domain.model.card.command.UpdateCardCommand;
import com.nl.logiceacards.domain.model.card.query.FindCardQuery;
import static com.nl.logiceacards.infrastructure.db.cards.repository.specification.CardSpecification.hasUserId;
import com.nl.logiceacards.infrastructure.db.exception.ResourceNotFoundException;
import com.nl.logiceacards.infrastructure.configuration.security.ApiTokenAuthFacade;
import com.nl.logiceacards.infrastructure.db.cards.entity.CardEntity;
import com.nl.logiceacards.infrastructure.db.cards.repository.CardsRepository;

import com.nl.logiceacards.infrastructure.db.users.entity.AppUserEntity;
import com.nl.logiceacards.infrastructure.web.responses.CardResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CardsRepositoryAdapter implements CardsRepositoryPort {
    
    private final CardsRepository cardsRepository;
    
    private final ApiTokenAuthFacade apiTokenAuthFacade;
    
    @Override
    public Card createCard(final Card card) {
        var user = apiTokenAuthFacade.getAuthenticatedUser();
        var cardEntity = CardsRepositoryAdapterMapper.INSTANCE.toEntity(card, user);
        cardsRepository.save(cardEntity);
        return Card.fromEntity(cardEntity, user.getEmail());
    }
    
    @Override
    public Page<CardResponse> findAll(final Pageable pageable) {
        var cardEntities = cardsRepository.findAll(pageable);
        var cardResponses = cardEntities.getContent()
                                        .stream()
                                        .map(CardsRepositoryAdapterMapper.INSTANCE::toDto)
                                        .toList();
        
        return new PageImpl<>(cardResponses, pageable, cardEntities.getTotalElements());
    }
    
    @Override
    public Card updateCard(final UpdateCardCommand command) {
        return cardsRepository.findById(command.cardId())
                              .map(cardEntity -> {
                                  updateCard(command, cardEntity);
                                  return cardsRepository.save(cardEntity);
                              }).map(CardsRepositoryAdapterMapper.INSTANCE::toDomain)
                              .orElseThrow(
                                  () -> new ResourceNotFoundException("Card not found for specific id : " + command.cardId()));
    }
    
    @Override
    public Card findCard(final FindCardQuery query) {
        return cardsRepository.findById(query.id())
                              .map(CardsRepositoryAdapterMapper.INSTANCE::toDomain)
                              .orElseThrow(
                                  () -> new ResourceNotFoundException("Card not found for specific id : " + query.id()));
    }
    
    @Override
    public List<Card> searchCards( Specification<CardEntity> spec) {
        var user = apiTokenAuthFacade.getAuthenticatedUser();
        spec = createUserSpecification(spec, user);
        
        var entities = cardsRepository.findAll(spec);
        return entities
                              .stream()
                              .map(CardsRepositoryAdapterMapper.INSTANCE::toDomain)
                              .toList();
    }
    
    
    @Override
    public void deleteCard(final DeleteCardCommand deleteCardCommand) {
        cardsRepository.findById(deleteCardCommand.cardId())
            .ifPresent(cardsRepository::delete);
        
    }
    
    public void updateCard(final UpdateCardCommand command, CardEntity cardEntity) {
        cardEntity.setName(command.name());
        cardEntity.setColor(command.color());
        cardEntity.setDescription(command.description());
        cardEntity.setStatus(command.status());
    }
    
    private static Specification<CardEntity> createUserSpecification(Specification<CardEntity> spec, final AppUserEntity user) {
        var role = user.getAuthorities()
                       .stream()
                       .map(GrantedAuthority::getAuthority)
                       .findFirst();
        if (role.isPresent()) {
            switch (role.get()) {
                case "ROLE_ADMIN":
                    break;
                case "ROLE_USER":
                    spec = Specification.where(spec).and(hasUserId(user.getId()));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + role.get());
            }
        }
        return spec;
    }
    
    @Mapper
    abstract static class CardsRepositoryAdapterMapper {
        
        private static final CardsRepositoryAdapterMapper INSTANCE = Mappers.getMapper(CardsRepositoryAdapterMapper.class);
        
        @Mapping(target = "appUserEntity", source = "user")
        @Mapping(target = "id", ignore = true)
        abstract CardEntity toEntity(Card card, AppUserEntity user);
        
        @Mapping(target = "userId", source = "entity.appUserEntity.username")
        abstract Card toDomain(CardEntity entity);
        
        abstract CardResponse toDto(CardEntity card);
        
    }
    
}

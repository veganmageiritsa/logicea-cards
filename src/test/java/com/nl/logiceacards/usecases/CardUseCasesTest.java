package com.nl.logiceacards.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.nl.logiceacards.ContainerIntegrationTest;
import com.nl.logiceacards.application.port.in.CreateCardUseCase;
import com.nl.logiceacards.application.port.in.DeleteCardUseCase;
import com.nl.logiceacards.application.port.in.FindCardUseCase;
import com.nl.logiceacards.application.port.in.SearchCardsUseCase;
import com.nl.logiceacards.application.port.in.UpdateCardUseCase;
import com.nl.logiceacards.domain.model.card.CardStatus;
import com.nl.logiceacards.domain.model.card.CardsFilter;
import com.nl.logiceacards.domain.model.card.command.CreateCardCommand;
import com.nl.logiceacards.domain.model.card.command.DeleteCardCommand;
import com.nl.logiceacards.domain.model.card.command.UpdateCardCommand;
import com.nl.logiceacards.domain.model.card.query.FindCardQuery;
import com.nl.logiceacards.infrastructure.db.users.repository.AppUserRepository;
import static org.junit.Assert.assertThrows;

class CardUseCasesTest extends ContainerIntegrationTest {
    
    private static final String TEST_CARD_NAME = "test card";
    
    private static final String TEST_CARD_COLOR = "#90fre";
    
    private static final String TEST_CARD_DESCRIPTION = "test description";
    
    @Autowired
    private CreateCardUseCase createCardUseCase;
    
    @Autowired
    private FindCardUseCase findCardUseCase;
    
    @Autowired
    private UpdateCardUseCase updateCardUseCase;
    
    @Autowired
    private DeleteCardUseCase deleteCardUseCase;
    
    @Autowired
    private AppUserRepository appUserRepository;
    
    @Autowired
    private SearchCardsUseCase searchCardsUseCase;
    
    @Test
    void crudCard(){
       
        var apiUser = appUserRepository.findById(1).orElseThrow();
        SecurityContextHolder.getContext()
                             .setAuthentication(new TestingAuthenticationToken(apiUser, apiUser.getUsername(), apiUser.getPassword()));
        CreateCardCommand createCardCommand = new CreateCardCommand("test card", "#90fre", "test description");
        
        var card = createCardUseCase.createCard(createCardCommand);
        Assertions.assertEquals(TEST_CARD_NAME, card.getName());
        Assertions.assertEquals(TEST_CARD_COLOR, card.getColor());
        Assertions.assertEquals(TEST_CARD_DESCRIPTION, card.getDescription());
        
        var updateCardCommand = new UpdateCardCommand(card.getId(), TEST_CARD_NAME, TEST_CARD_COLOR, TEST_CARD_DESCRIPTION, CardStatus.IN_PROGRESS);
        var updatedCard = updateCardUseCase.updateCard(updateCardCommand);
        Assertions.assertEquals(CardStatus.IN_PROGRESS, updatedCard.getStatus());
        Assertions.assertNotNull(findCardUseCase.findCard(new FindCardQuery(card.getId())));
        
        deleteCardUseCase.deleteCard(new DeleteCardCommand(card.getId()));
    }
    
    @Test
     void accessDeniedControlOnFindCard(){
        
        var apiUser = appUserRepository.findById(2).orElseThrow();
        SecurityContextHolder.getContext()
                             .setAuthentication(new TestingAuthenticationToken(apiUser, apiUser.getUsername(), apiUser.getPassword()));
        
        assertThrows(AuthorizationDeniedException.class, () -> findCardUseCase.findCard(new FindCardQuery(1)));
        
    }
    
    @Test
    void searchCardsTestAdmin(){
        var apiUser = appUserRepository.findById(1).orElseThrow();
        SecurityContextHolder.getContext()
                             .setAuthentication(new TestingAuthenticationToken(apiUser, apiUser.getUsername(), apiUser.getPassword()));
        
        CardsFilter filter = new CardsFilter("Feature", null, null, null, null, null, null, null, null, null);
        var cards = searchCardsUseCase.searchCards(filter);
        
        Assertions.assertEquals(3, cards.getContent().size());
    }
    
    @Test
    void searchCardsTestUser(){
        var apiUser = appUserRepository.findById(2).orElseThrow();
        SecurityContextHolder.getContext()
                             .setAuthentication(new TestingAuthenticationToken(apiUser, apiUser.getUsername(), apiUser.getPassword()));
        
        CardsFilter filter = new CardsFilter("Feature", null, null, null, null, null, null, null, null, null);
        var cards = searchCardsUseCase.searchCards(filter);
        
        Assertions.assertEquals(1, cards.getContent().size());
    }
    
    @Test
    void searchCardsWithUserId() {
        var apiUser = appUserRepository.findById(2).orElseThrow();
        SecurityContextHolder.getContext()
                             .setAuthentication(new TestingAuthenticationToken(apiUser, apiUser.getUsername(), apiUser.getPassword()));
        
        CardsFilter filter = new CardsFilter(null, null, null, null, null, 2, null, null, null, null);
        var cards = searchCardsUseCase.searchCards(filter);
        
        Assertions.assertEquals(2, cards.getContent().size());
    }
    
}

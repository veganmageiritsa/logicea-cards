package com.nl.logiceacards.controllers;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import com.nl.logiceacards.ContainerIntegrationTest;
import com.nl.logiceacards.domain.model.card.CardStatus;
import com.nl.logiceacards.infrastructure.db.cards.repository.criteria.CardSearchCriteria;
import com.nl.logiceacards.infrastructure.db.users.repository.AppUserRepository;
import com.nl.logiceacards.infrastructure.web.requests.CreateCardRequest;
import com.nl.logiceacards.infrastructure.web.requests.UpdateCardRequest;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.securityContext;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class CardControllerTest extends ContainerIntegrationTest {
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private AppUserRepository appUserRepository;
    
    @Test
    void controllerTestGetCardRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/app/cards/{cardId}", 1)
                                              .with(user("user").roles("ADMIN"))
                                              .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", is(1)));
        
    }
    
    @Test
    void controllerTestGetCardRequestForbidden() throws Exception {
        var apiUser = appUserRepository.findById(2).orElseThrow();
        var securityContext = SecurityContextHolder.getContext();
        securityContext
            .setAuthentication(new TestingAuthenticationToken(apiUser, apiUser.getUsername(), apiUser.getPassword()));
        mockMvc.perform(MockMvcRequestBuilders.get("/app/cards/{cardId}", 1)
                                              .with(securityContext(securityContext)))
               .andExpect(status().isForbidden());
        
    }
    
    @Test
    void controllerCreateCardRequest() throws Exception {
        var apiUser = appUserRepository.findById(1).orElseThrow();
        var securityContext = SecurityContextHolder.getContext();
        securityContext
            .setAuthentication(new UsernamePasswordAuthenticationToken(apiUser, null, apiUser.getAuthorities()));
        CreateCardRequest createCardRequest = new CreateCardRequest("Chip", "#fer12", "Test Feature");
        mockMvc.perform(MockMvcRequestBuilders
                            .post("/app/cards")
                            .content(objectMapper.writeValueAsString(createCardRequest))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated())
               .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
        
    }
    
    @Test
    void controllerCreateCardRequestInvalidRequest() throws Exception {
        var apiUser = appUserRepository.findById(1).orElseThrow();
        var securityContext = SecurityContextHolder.getContext();
        securityContext
            .setAuthentication(new UsernamePasswordAuthenticationToken(apiUser, null, apiUser.getAuthorities()));
        CreateCardRequest createCardRequest = new CreateCardRequest("Chip", "#fer1243", "Test Feature");
        mockMvc.perform(MockMvcRequestBuilders
                            .post("/app/cards")
                            .content(objectMapper.writeValueAsString(createCardRequest))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest());
        
    }
    
    @Test
    void controllerUpdateCardRequest() throws Exception {
        var apiUser = appUserRepository.findById(2).orElseThrow();
        var securityContext = SecurityContextHolder.getContext();
        securityContext
            .setAuthentication(new UsernamePasswordAuthenticationToken(apiUser, null, apiUser.getAuthorities()));
        
        UpdateCardRequest updateCardRequest = new UpdateCardRequest(CardStatus.IN_PROGRESS);
        updateCardRequest.setName("Fit");
        mockMvc.perform(MockMvcRequestBuilders
                            .put("/app/cards/{cardId}", 5)
                            .content(objectMapper.writeValueAsString(updateCardRequest))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
               .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(5))
               .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Fit"))
               .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("IN_PROGRESS"));
        
    }
    
    @Test
    void controllerDeleteCardRequestForbidden() throws Exception {
        var apiUser = appUserRepository.findById(2).orElseThrow();
        var securityContext = SecurityContextHolder.getContext();
        securityContext
            .setAuthentication(new UsernamePasswordAuthenticationToken(apiUser, null, apiUser.getAuthorities()));
        
        mockMvc.perform(MockMvcRequestBuilders
                            .delete("/app/cards/{cardId}", 1)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isForbidden());
        
    }
    
    @Test
    void controllerSearchCardsRequest() throws Exception {
        var apiUser = appUserRepository.findById(2).orElseThrow();
        var securityContext = SecurityContextHolder.getContext();
        securityContext
            .setAuthentication(new UsernamePasswordAuthenticationToken(apiUser, null, apiUser.getAuthorities()));
        CardSearchCriteria criteria = new CardSearchCriteria();
        criteria.setCreatedAt(Date.valueOf("2024-09-03"));
        mockMvc.perform(MockMvcRequestBuilders
                            .get("/app/search")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.content", hasSize(2)))
               .andExpectAll(
                   MockMvcResultMatchers.jsonPath("$.content..[0].id").value(4),
                   MockMvcResultMatchers.jsonPath("$.content..[1].id").value(5)
               );
    }
    
}

package com.nl.logiceacards.infrastructure.db.cards.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Repository;

import com.nl.logiceacards.infrastructure.db.cards.entity.CardEntity;
import jakarta.annotation.Nullable;

@Repository
public interface CardsRepository extends JpaRepository<CardEntity, Integer>, JpaSpecificationExecutor<CardEntity> {
    
    
    @PostAuthorize("hasRole('ADMIN') || @securityService.isResourceOwner(returnObject)")
    Optional<CardEntity> findById(int cardId); // <1>
    
    Optional<CardEntity> findByIdAndAppUserEntityId(int cardId, int userId); // <1>
    
    @Override
    Page<CardEntity> findAll(@Nullable Specification<CardEntity> spec, @NonNull Pageable pageable);
    
    @Override
    List<CardEntity> findAll(Specification<CardEntity> spec ); // <1>
    
}

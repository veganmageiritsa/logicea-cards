package com.nl.logiceacards.infrastructure.db.cards.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.nl.logiceacards.domain.model.card.CardStatus;
import com.nl.logiceacards.domain.model.card.command.UpdateCardCommand;
import com.nl.logiceacards.infrastructure.db.users.entity.AppRoleType;
import com.nl.logiceacards.infrastructure.db.users.entity.AppUserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name = "cards")
@AllArgsConstructor
@DynamicUpdate
public class CardEntity implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -8486718468133038441L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;
    
    private String color;
    
    private String description;
    
    @Enumerated(EnumType.STRING)
    private CardStatus status;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    private AppUserEntity appUserEntity;
    
    
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
    
   
    
}

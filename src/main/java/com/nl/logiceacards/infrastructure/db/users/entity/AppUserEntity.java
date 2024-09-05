package com.nl.logiceacards.infrastructure.db.users.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.ToString;

@Table(name = "app_users",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "password"),
           @UniqueConstraint(columnNames = "email")
       })
@Entity
@ToString
public class AppUserEntity implements UserDetails, Serializable {
    
    
    private static final long serialVersionUID = 1370851881897493481L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    private String username;
    
    @Column(unique = true, length = 100, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
    
    
    @ManyToMany
    @Fetch(FetchMode.JOIN)
           @JoinTable(name = "app_users_roles",
           joinColumns = @JoinColumn(name = "app_user_id"),
           inverseJoinColumns = @JoinColumn(name = "app_role_id"))
    private Set<AppRoleEntity> roles = new HashSet<>();
    
    @Override
    
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return roles
            .stream()
            .map(role -> new SimpleGrantedAuthority( role.getName().toString()))
            .toList();
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }
    
    @Override
    public String getUsername() {
        return email;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public Integer getId() {
        return id;
    }
    
    public AppUserEntity setId(Integer id) {
        this.id = id;
        return this;
    }
    
    public String getEmail() {
        return email;
    }
    
    public AppUserEntity setEmail(String email) {
        this.email = email;
        return this;
    }
    
    public AppUserEntity setPassword(String password) {
        this.password = password;
        return this;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    public AppUserEntity setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }
    
    public Date getUpdatedAt() {
        return updatedAt;
    }
    
    public AppUserEntity setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
    
    
}
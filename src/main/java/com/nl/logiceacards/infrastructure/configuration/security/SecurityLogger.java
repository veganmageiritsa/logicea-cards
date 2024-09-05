package com.nl.logiceacards.infrastructure.configuration.security;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.security.access.event.AuthorizationFailureEvent;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityLogger {
    @EventListener
    public void authenticated(final @NonNull AuthenticationSuccessEvent event) {
        final Object principal = event.getAuthentication().getPrincipal();
        log.info("Successful login - [username: \"{}\"]", principal);
    }
    @EventListener
    public void authenticationFailure(final @NonNull AbstractAuthenticationFailureEvent event) {
        final Object principal = event.getAuthentication().getPrincipal();
        log.info("Unsuccessful login - [username: \"{}\"]", principal);
    }
    @EventListener
    public void authorizationFailure(final @NonNull AuthorizationDeniedEvent event) {
        final Object principal = event.getAuthentication().get()     ;
        final String message = event.getAuthorizationDecision().toString();
        log.error("Unauthorized access - [username: \"{}\", message: \"{}\"]", principal, Optional.ofNullable(message).orElse("<null>"));
    }
    @EventListener
    public void logoutSuccess(final @NonNull LogoutSuccessEvent event) {
        final Object principal = event.getAuthentication().getPrincipal();
        log.info("Successful logout - [username: \"{}\"]", principal);
    }
}

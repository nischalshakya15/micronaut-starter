package org.personal.auth;

import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent;
import io.micronaut.security.token.refresh.RefreshTokenPersistence;
import io.reactivex.Flowable;
import org.personal.entity.roles.Role;
import org.personal.entity.users.UserService;
import org.personal.exceptions.TokenNotFoundException;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.stream.Collectors;

@Singleton
public class RefreshTokenProvider implements RefreshTokenPersistence {

    @Inject
    private UserService userService;

    @Override
    @EventListener
    public void persistToken(RefreshTokenGeneratedEvent event) {
        userService.saveRefreshToken(event.getUserDetails().getUsername(), event.getRefreshToken());
    }

    @Override
    public Publisher<UserDetails> getUserDetails(String refreshToken) {
        return userService.findByRefreshToken(refreshToken).map(user ->
                Flowable.just(new UserDetails(user.getUsername(), user.getRoles().stream().map(Role::getName).collect(Collectors.toList())))
        ).orElse(Flowable.error(new TokenNotFoundException("Token not found")));
    }
}
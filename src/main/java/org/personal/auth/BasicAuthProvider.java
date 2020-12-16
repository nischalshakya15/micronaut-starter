package org.personal.auth;

import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.*;
import io.reactivex.Flowable;
import org.personal.entity.roles.Role;
import org.personal.entity.users.User;
import org.personal.entity.users.UserService;
import org.reactivestreams.Publisher;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.micronaut.security.authentication.AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH;
import static io.micronaut.security.authentication.AuthenticationFailureReason.USER_NOT_FOUND;

@Singleton
public class BasicAuthProvider implements AuthenticationProvider {

    @Inject
    private UserService userService;

    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest,
                                                          AuthenticationRequest<?, ?> authenticationRequest) {
        final String username = authenticationRequest.getIdentity().toString();
        final String password = authenticationRequest.getSecret().toString();
        Optional<User> user = userService.findByUsernameAndPassword(username, password);
        return Flowable.just(
                user.map(u -> {
                    if (u.getPassword().equals(password)) {
                        return new UserDetails(username, getRoles(u));
                    }
                    return new AuthenticationFailed(CREDENTIALS_DO_NOT_MATCH);
                }).orElse(new AuthenticationFailed(USER_NOT_FOUND))
        );
    }

    private List<String> getRoles(User u) {
        return u.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }
}

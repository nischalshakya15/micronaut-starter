package org.personal.entity.users;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Repository;
import org.personal.base.BaseRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

    Optional<User> findByRefreshToken(String refreshToken);

    @Override
    @Join(value = "roles", type = Join.Type.FETCH)
    List<User> findAll();
}

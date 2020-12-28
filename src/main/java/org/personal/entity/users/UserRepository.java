package org.personal.entity.users;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import org.personal.base.BaseRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {

    @Query("SELECT u FROM User u WHERE u.username = :username and password = :password or email = :email")
    Optional<User> authenticate(String username, String password, String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByRefreshToken(String refreshToken);

    @Override
    @Join(value = "roles", type = Join.Type.FETCH)
    List<User> findAll();
}

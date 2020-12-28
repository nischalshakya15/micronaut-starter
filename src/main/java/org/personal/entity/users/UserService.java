package org.personal.entity.users;

import org.personal.base.BaseService;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class UserService extends BaseService<User, UserDto> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        super(userRepository, userMapper);
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsernameAndPassword(String usernameOrEmail, String password) {
        return userRepository
                .authenticate(usernameOrEmail, password, usernameOrEmail);
    }

    public Optional<User> findByRefreshToken(String refreshToken) {
        return userRepository.findByRefreshToken(refreshToken);
    }

    public void saveRefreshToken(String username, String refreshToken) {
        userRepository.findByUsername(username).ifPresent(
                user -> {
                    user.setRefreshToken(refreshToken);
                    userRepository.update(user);
                }
        );
    }
}

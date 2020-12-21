package org.personal.entity.users;

import org.personal.exceptions.ResourceNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserMapper userMapper;

    public List<UserDto> findAll() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> userMapper.toDto(user))
                .collect(Collectors.toList());
    }

    public UserDto save(UserDto userDto) {
        User user = userRepository.save(userMapper.toEntity(userDto));
        return userMapper.toDto(user);
    }

    public User update(User user) {
        return userRepository.update(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource with given id not found"));
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return userRepository
                .findByUsernameAndPassword(username, password);
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

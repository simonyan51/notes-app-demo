package io.gnelsimonyan.users.persistence;

import io.gnelsimonyan.users.User;
import io.gnelsimonyan.users.boundaries.output.FindUserOutputBoundary;
import org.springframework.stereotype.Component;

@Component
public class UserOutputBoundaryAdapter implements FindUserOutputBoundary {

    private final UserRepository userRepository;

    public UserOutputBoundaryAdapter(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        return User.of(
                userEntity.email(),
                userEntity.password()
        );
    }
}

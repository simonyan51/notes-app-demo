package io.gnelsimonyan.users.persistence;

import io.gnelsimonyan.users.user.User;
import io.gnelsimonyan.users.boundaries.output.FindUserOutputBoundary;
import org.springframework.stereotype.Component;


@Component
class UserOutputBoundaryAdapter implements FindUserOutputBoundary {

    private final UserRepository userRepository;

    UserOutputBoundaryAdapter(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByEmail(final String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) return null;

        return User.of(
                userEntity.id(),
                userEntity.email(),
                userEntity.password(),
                userEntity.createdAt(),
                userEntity.updatedAt()
        );
    }
}

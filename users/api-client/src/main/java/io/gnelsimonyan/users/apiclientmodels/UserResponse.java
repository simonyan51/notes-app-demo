package io.gnelsimonyan.users.apiclientmodels;

import java.time.LocalDateTime;

public interface UserResponse {
    Long id();

    String email();

    LocalDateTime createdAt();

    LocalDateTime updatedAt();

    static UserResponse of(
            Long id,
            String email,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        return new UserResponseImpl(
                id,
                email,
                createdAt,
                updatedAt
        );
    }

    record UserResponseImpl(
            Long id,
            String email,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) implements UserResponse {}
}

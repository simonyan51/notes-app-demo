package io.gnelsimonyan.users.rest.endpoints.dtos;

import java.time.LocalDateTime;

public class UserResponse {
    private final Long id;

    private final String email;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public UserResponse(
            final Long id,
            final String email,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ) {
        this.id = id;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}


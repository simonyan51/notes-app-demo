package io.gnelsimonyan.users.rest.endpoints.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Getter
@AllArgsConstructor(staticName = "of")
public class UserResponse {
    private final Long id;

    private final String email;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;
}


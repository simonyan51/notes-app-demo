package io.gnelsimonyan.notes.externalapis.userapi;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizedUser {
    private Long id;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

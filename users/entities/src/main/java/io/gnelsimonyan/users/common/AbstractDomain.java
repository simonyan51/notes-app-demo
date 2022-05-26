package io.gnelsimonyan.users.common;

import java.time.LocalDateTime;

public interface AbstractDomain {
    Long id();

    LocalDateTime createdAt();

    LocalDateTime updatedAt();
}

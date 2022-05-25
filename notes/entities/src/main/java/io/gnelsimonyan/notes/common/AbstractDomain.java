package io.gnelsimonyan.notes.common;

import java.time.LocalDateTime;

public interface AbstractDomain {
    Long id();

    LocalDateTime createdAt();

    LocalDateTime updatedAt();
}

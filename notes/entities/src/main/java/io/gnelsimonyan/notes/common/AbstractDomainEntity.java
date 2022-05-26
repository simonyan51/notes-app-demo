package io.gnelsimonyan.notes.common;

import java.time.LocalDateTime;

public abstract class AbstractDomainEntity implements AbstractDomain {

    protected Long id;

    protected LocalDateTime createdAt;

    protected LocalDateTime updatedAt;

    public AbstractDomainEntity() {
        createdAt = LocalDateTime.now();
    }

    public AbstractDomainEntity(
            final Long id,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long id() {
        return id;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}

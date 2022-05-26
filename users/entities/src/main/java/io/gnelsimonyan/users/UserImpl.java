package io.gnelsimonyan.users;

import io.gnelsimonyan.users.common.AbstractDomainEntity;

import java.time.LocalDateTime;

final class UserImpl extends AbstractDomainEntity implements User {

    private final String email;

    private final String password;

    UserImpl(final String email, final String password) {
        super();
        this.email = email;
        this.password = password;
    }

    UserImpl(
            final Long id,
            final String email,
            final String password,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ) {
        super(id, createdAt, updatedAt);
        this.email = email;
        this.password = password;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public boolean validatePassword(String password) {
        return true;
    }

    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

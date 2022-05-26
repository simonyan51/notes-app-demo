package io.gnelsimonyan.users.persistence;

import io.gnelsimonyan.users.persistence.common.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
class UserEntity extends AbstractEntity {

    @Column(name="email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    static UserEntity of(
            final Long id,
            final String email,
            final String password,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ) {
        return new UserEntity(
                id,
                email,
                password,
                createdAt,
                updatedAt
        );
    }

    public UserEntity() {
        super();
    }

    public UserEntity(
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

    public String email() {
        return email;
    }

    public String password() {
        return password;
    }

}

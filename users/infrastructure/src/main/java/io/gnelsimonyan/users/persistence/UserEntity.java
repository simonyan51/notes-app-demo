package io.gnelsimonyan.users.persistence;

import io.gnelsimonyan.users.persistence.common.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "users", indexes = {
        @Index(columnList = "email", name = "idx_email")
})
public class UserEntity extends AbstractEntity {

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public UserEntity() {
        super();
    }

    public String email() {
        return email;
    }

    public String password() {
        return password;
    }

}

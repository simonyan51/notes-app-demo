/*
 * Created by Gnel Simonyan
 * Created on 25/05/2022 18:58
 *
 * Copyright (c) 2022 PicsArt.Inc
 */

package io.gnelsimonyan.notes.persistence.user.entities;

import io.gnelsimonyan.notes.common.AbstractDomainEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class UserEntity extends AbstractDomainEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public UserEntity() {
        super();
    }

    public UserEntity(
            final Long id,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt,
            final Long id1,
            final String email,
            final String password) {
        super(id, createdAt, updatedAt);
        this.id = id1;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

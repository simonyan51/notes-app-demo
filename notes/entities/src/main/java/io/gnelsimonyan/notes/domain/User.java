/*
 * Created by Gnel Simonyan
 * Created on 25/05/2022 10:40
 *
 * Copyright (c) 2022 PicsArt.Inc
 */

package io.gnelsimonyan.notes.domain;

import io.gnelsimonyan.notes.common.AbstractDomain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User extends AbstractDomain {

    private String email;

    private String password;

    private List<Note> notes;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.notes = new ArrayList<>();
    }

    public User(
            Long id,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            String email,
            String password,
            List<Note> notes
    ) {
        super(id, createdAt, updatedAt);
        this.email = email;
        this.password = password;
        this.notes = notes;
    }

    public String email() {
        return email;
    }

    public String password() {
        return password;
    }

    public List<Note> notes() {
        return notes;
    }
}

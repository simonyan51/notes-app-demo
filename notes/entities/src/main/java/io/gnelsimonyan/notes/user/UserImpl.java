/*
 * Created by Gnel Simonyan
 * Created on 25/05/2022 10:40
 *
 * Copyright (c) 2022 PicsArt.Inc
 */

package io.gnelsimonyan.notes.user;

import io.gnelsimonyan.notes.common.AbstractDomainEntity;
import io.gnelsimonyan.notes.note.Note;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

final class UserImpl extends AbstractDomainEntity implements User {

    private final String email;

    private final String password;

    private final List<Note> notes;

    UserImpl(String email, String password) {
        super();
        this.email = email;
        this.password = password;
        this.notes = new ArrayList<>();
    }

    UserImpl(
            Long id,
            String email,
            String password,
            List<Note> notes,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
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

    public Note findNoteById(long noteId) {
        return notes.stream()
                .filter(note -> note.id() == noteId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", notes=" + notes +
                '}';
    }
}

package io.gnelsimonyan.notes.user;

import io.gnelsimonyan.notes.common.AbstractDomain;
import io.gnelsimonyan.notes.note.Note;

import java.time.LocalDateTime;
import java.util.List;

public interface User extends AbstractDomain {
    String email();

    String password();

    List<Note> notes();

    Note findNoteById(long noteId);

    static User of(final String email, final String password) {
        return new UserImpl(email, password);
    }

    static User of(
            final Long id,
            final String email,
            final String password,
            final List<Note> notes,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ) {
        return new UserImpl(
                id,
                email,
                password,
                notes,
                createdAt,
                updatedAt
        );
    }
}

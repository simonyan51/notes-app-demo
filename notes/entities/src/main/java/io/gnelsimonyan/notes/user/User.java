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

    static User of(String email, String password) {
        return new UserImpl(email, password);
    }

    static User of(
            Long id,
            String email,
            String password,
            List<Note> notes,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
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

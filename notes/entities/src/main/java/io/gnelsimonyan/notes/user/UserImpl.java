package io.gnelsimonyan.notes.user;

import io.gnelsimonyan.notes.common.AbstractDomainEntity;
import io.gnelsimonyan.notes.Note;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

final class UserImpl extends AbstractDomainEntity implements User {

    private final String email;

    private final String password;

    private final List<Note> notes;

    UserImpl(final String email, final String password) {
        super();
        this.email = email;
        this.password = password;
        this.notes = new ArrayList<>();
    }

    UserImpl(
            final Long id,
            final String email,
            final String password,
            final List<Note> notes,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
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

    public Note findNoteById(final long noteId) {
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

package io.gnelsimonyan.notes.note;

import io.gnelsimonyan.notes.common.AbstractDomain;

import java.time.LocalDateTime;

public interface Note extends AbstractDomain {
    Long userId();

    String title();

    String note();

    void changeTitle(String title);

    void changeNote(String note);

    static Note of(
            Long userId,
            String title,
            String note) {
        return new NoteImpl(
                userId,
                title,
                note);
    }

    static Note of(
            Long id,
            Long userId,
            String title,
            String note,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new NoteImpl(
                id,
                userId,
                title,
                note,
                createdAt,
                updatedAt);
    }
}

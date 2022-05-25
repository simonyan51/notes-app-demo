package io.gnelsimonyan.notes.note;

import io.gnelsimonyan.notes.common.AbstractDomain;

import java.time.LocalDateTime;

public interface Note extends AbstractDomain {
    Long userId();

    String title();

    String text();

    void changeTitle(String title);

    void changeText(String text);

    static Note of(
            final Long userId,
            final String title,
            final String text
    ) {
        return new NoteImpl(
                userId,
                title,
                text
        );
    }

    static Note of(
            final Long id,
            final Long userId,
            final String title,
            final String text,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ) {
        return new NoteImpl(
                id,
                userId,
                title,
                text,
                createdAt,
                updatedAt
        );
    }
}

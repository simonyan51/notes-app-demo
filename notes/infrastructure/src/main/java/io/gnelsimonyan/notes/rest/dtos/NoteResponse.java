package io.gnelsimonyan.notes.rest.dtos;

import java.time.LocalDateTime;

public interface NoteResponse {
    Long id();

    String title();

    String text();

    LocalDateTime createdAt();

    LocalDateTime updatedAt();

    static NoteResponse of(
           final Long id,
           final String title,
           final String text,
           final LocalDateTime createdAt,
           final LocalDateTime updatedAt
    ) {
        return new NoteResponseImpl(
                id,
                title,
                text,
                createdAt,
                updatedAt
        );
    }

    record NoteResponseImpl(
            Long id,
            String title,
            String text,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) implements NoteResponse {}
}

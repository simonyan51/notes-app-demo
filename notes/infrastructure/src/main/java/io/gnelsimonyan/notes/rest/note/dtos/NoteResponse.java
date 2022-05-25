package io.gnelsimonyan.notes.rest.note.dtos;

import java.time.LocalDateTime;

public class NoteResponse {
    private final Long id;

    private final String title;

    private final String text;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public NoteResponse(Long id, String title, String text, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}

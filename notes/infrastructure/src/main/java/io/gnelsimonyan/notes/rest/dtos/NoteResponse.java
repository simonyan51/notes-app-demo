package io.gnelsimonyan.notes.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor(staticName = "of")
public class NoteResponse {
    private final Long id;

    private final String title;

    private final String text;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;
}

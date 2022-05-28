package io.gnelsimonyan.notes.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class SaveNoteRequest {
    @NotNull(message = "title must be provided")
    @Size(min = 1, max = 50, message = "title length must not exceed 50")
    private final String title;

    @Size(max = 1000, message = "text length must not exceed 1000")
    private final String text;
}

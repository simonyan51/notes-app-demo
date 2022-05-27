package io.gnelsimonyan.notes.rest.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public abstract class SaveNoteRequest {
    @NotNull(message = "title must be provided")
    @Size(min = 1, max = 50, message = "title length must not exceed 50")
    protected final String title;

    @Size(max = 1000, message = "text length must not exceed 1000")
    protected final String text;

    public SaveNoteRequest(final String title, final String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}

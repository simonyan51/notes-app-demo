package io.gnelsimonyan.notes.rest.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

public abstract class SaveNoteRequest {
    @NotEmpty(message = "title must be provided")
    @Max(value = 50, message = "title length must not exceed 50")
    protected final String title;

    @Max(value = 1000, message = "text length must not exceed 1000")
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

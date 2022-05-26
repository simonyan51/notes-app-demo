package io.gnelsimonyan.notes.rest.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

public abstract class SaveNoteRequest {
    @NotEmpty
    @Max(50)
    protected final String title;

    @Max(1000)
    protected final String text;

    public SaveNoteRequest(String title, String text) {
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

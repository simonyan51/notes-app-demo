/*
 * Created by Gnel Simonyan
 * Created on 25/05/2022 16:50
 *
 * Copyright (c) 2022 PicsArt.Inc
 */

package io.gnelsimonyan.notes.rest.note.dtos;

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

    public String title() {
        return title;
    }

    public String text() {
        return text;
    }
}

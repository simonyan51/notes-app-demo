/*
 * Created by Gnel Simonyan
 * Created on 25/05/2022 16:51
 *
 * Copyright (c) 2022 PicsArt.Inc
 */

package io.gnelsimonyan.notes.rest.note.dtos;

public class CreateNoteRequest extends SaveNoteRequest {
    public CreateNoteRequest(String title, String text) {
        super(title, text);
    }
}

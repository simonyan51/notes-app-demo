/*
 * Created by Gnel Simonyan
 * Created on 25/05/2022 17:07
 *
 * Copyright (c) 2022 PicsArt.Inc
 */

package io.gnelsimonyan.notes.rest.dtos.note;

public class UpdateNoteRequest extends SaveNoteRequest {
    public UpdateNoteRequest(String title, String text) {
        super(title, text);
    }
}

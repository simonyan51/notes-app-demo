package io.gnelsimonyan.notes.rest.dtos;

public class CreateNoteRequest extends SaveNoteRequest {
    public CreateNoteRequest(final String title, final String text) {
        super(title, text);
    }
}

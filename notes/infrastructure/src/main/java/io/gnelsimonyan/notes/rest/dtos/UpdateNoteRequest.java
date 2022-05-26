package io.gnelsimonyan.notes.rest.dtos;

public class UpdateNoteRequest extends SaveNoteRequest {
    public UpdateNoteRequest(String title, String text) {
        super(title, text);
    }
}

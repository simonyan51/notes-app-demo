package io.gnelsimonyan.notes.rest.dtos;

public class CreateNoteRequest extends SaveNoteRequest {
    public CreateNoteRequest(String title, String text) {
        super(title, text);
    }
}

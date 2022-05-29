package io.gnelsimonyan.notes.exceptions;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
public class NoteNotFoundException extends Exception {
    private long noteId;

    public NoteNotFoundException(final String message) {
        super(message);
    }

    public NoteNotFoundException(final String message, final long noteId) {
        super(message);
        this.noteId = noteId;
    }
}

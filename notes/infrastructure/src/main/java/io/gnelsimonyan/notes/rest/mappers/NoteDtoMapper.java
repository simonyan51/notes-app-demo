package io.gnelsimonyan.notes.rest.mappers;

import io.gnelsimonyan.notes.Note;
import io.gnelsimonyan.notes.boundaries.input.params.SaveUserNoteParams;
import io.gnelsimonyan.notes.rest.dtos.NoteResponse;
import io.gnelsimonyan.notes.rest.dtos.SaveNoteRequest;

public interface NoteDtoMapper {
    static NoteResponse mapNoteToNoteResponse(final Note note) {
        return new NoteResponse(
                note.id(),
                note.title(),
                note.text(),
                note.createdAt(),
                note.updatedAt()
        );
    }

    static SaveUserNoteParams mapNoteRequestToSaveUserNoteParams(
            final long userId,
            final SaveNoteRequest createNoteRequest
    ) {
        return SaveUserNoteParams.of(
                userId,
                createNoteRequest.getTitle(),
                createNoteRequest.getText()
        );
    }
}

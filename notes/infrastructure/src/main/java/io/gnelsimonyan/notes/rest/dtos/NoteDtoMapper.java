/*
 * Created by Gnel Simonyan
 * Created on 25/05/2022 16:09
 *
 * Copyright (c) 2022 PicsArt.Inc
 */

package io.gnelsimonyan.notes.rest.dtos;

import io.gnelsimonyan.notes.Note;
import io.gnelsimonyan.notes.boundaries.input.params.SaveUserNoteParams;

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
                createNoteRequest.title(),
                createNoteRequest.text()
        );
    }
}

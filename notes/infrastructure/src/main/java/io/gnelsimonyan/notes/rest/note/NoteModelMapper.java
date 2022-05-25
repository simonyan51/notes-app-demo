/*
 * Created by Gnel Simonyan
 * Created on 25/05/2022 16:09
 *
 * Copyright (c) 2022 PicsArt.Inc
 */

package io.gnelsimonyan.notes.rest.mappers;

import io.gnelsimonyan.notes.boundaries.input.params.SaveUserNoteParams;
import io.gnelsimonyan.notes.note.Note;
import io.gnelsimonyan.notes.rest.dtos.note.NoteResponse;
import io.gnelsimonyan.notes.rest.dtos.note.SaveNoteRequest;

public interface NoteMapper {
    static NoteResponse mapNoteToNoteResponse(Note note) {
        return new NoteResponse(
                note.id(),
                note.title(),
                note.text(),
                note.createdAt(),
                note.updatedAt()
        );
    }

    static SaveUserNoteParams mapNoteRequestToSaveUserNoteParams(
            long userId,
            SaveNoteRequest createNoteRequest
    ) {
        return SaveUserNoteParams.of(
                userId,
                createNoteRequest.title(),
                createNoteRequest.text()
        );
    }
}

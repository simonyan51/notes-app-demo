/*
 * Created by Gnel Simonyan
 * Created on 25/05/2022 15:21
 *
 * Copyright (c) 2022 PicsArt.Inc
 */

package io.gnelsimonyan.notes.rest.endpoints;

import io.gnelsimonyan.notes.boundaries.input.CreateUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.FindUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.RemoveUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.UpdateUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.params.SaveUserNoteParams;
import io.gnelsimonyan.notes.rest.dtos.NoteResponse;
import io.gnelsimonyan.notes.rest.mappers.NotesResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/notes")
public class NoteEndpoint {

    private final FindUserNoteInputBoundary findUserNoteInputBoundary;

    private final CreateUserNoteInputBoundary createUserNoteInputBoundary;

    private final UpdateUserNoteInputBoundary updateUserNoteInputBoundary;

    private final RemoveUserNoteInputBoundary removeUserNoteInputBoundary;

    public NoteEndpoint(
            final FindUserNoteInputBoundary findUserNoteInputBoundary,
            final CreateUserNoteInputBoundary createUserNoteInputBoundary,
            final UpdateUserNoteInputBoundary updateUserNoteInputBoundary,
            final RemoveUserNoteInputBoundary removeUserNoteInputBoundary
    ) {
        this.findUserNoteInputBoundary = findUserNoteInputBoundary;
        this.createUserNoteInputBoundary = createUserNoteInputBoundary;
        this.updateUserNoteInputBoundary = updateUserNoteInputBoundary;
        this.removeUserNoteInputBoundary = removeUserNoteInputBoundary;
    }

    @GetMapping
    public ResponseEntity<List<NoteResponse>> getNotes() {
        List<NoteResponse> noteResponseList = findUserNoteInputBoundary.findUserNotes(null)
                .stream()
                .map(NotesResponseMapper::mapNoteToNoteResponse)
                .toList();

        return ResponseEntity.ok(noteResponseList);
    }

    @GetMapping("{id}")
    public ResponseEntity<NoteResponse> getNote(@PathVariable Long noteId) {
        NoteResponse noteResponse = NotesResponseMapper.mapNoteToNoteResponse(
                findUserNoteInputBoundary.findUserNote(noteId, null)
        );

        return ResponseEntity.ok(noteResponse);
    }

}

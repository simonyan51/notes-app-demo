/*
 * Created by Gnel Simonyan
 * Created on 25/05/2022 15:21
 *
 * Copyright (c) 2022 PicsArt.Inc
 */

package io.gnelsimonyan.notes.rest.note;

import io.gnelsimonyan.notes.boundaries.input.CreateUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.FindUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.RemoveUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.UpdateUserNoteInputBoundary;
import io.gnelsimonyan.notes.note.Note;
import io.gnelsimonyan.notes.rest.note.dtos.CreateNoteRequest;
import io.gnelsimonyan.notes.rest.note.dtos.NoteResponse;
import io.gnelsimonyan.notes.rest.note.dtos.UpdateNoteRequest;
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
                .map(NoteModelMapper::mapNoteToNoteResponse)
                .toList();

        return ResponseEntity.ok(noteResponseList);
    }

    @GetMapping("{id}")
    public ResponseEntity<NoteResponse> getNote(@PathVariable("id") Long noteId) {
        Note note = findUserNoteInputBoundary.findUserNote(noteId, null);

        return ResponseEntity.ok(NoteModelMapper.mapNoteToNoteResponse(note));
    }

    @PostMapping
    public ResponseEntity<NoteResponse> createNote(@RequestBody CreateNoteRequest createNoteRequest) {
        Note createdNote = createUserNoteInputBoundary.createUserNote(
                NoteModelMapper.mapNoteRequestToSaveUserNoteParams(0, createNoteRequest)
        );

        return ResponseEntity.ok(NoteModelMapper.mapNoteToNoteResponse(createdNote));
    }

    @PutMapping("{id}")
    public ResponseEntity<NoteResponse> updateNote(
            @PathVariable("id") long noteId,
            @RequestBody UpdateNoteRequest updateNoteRequest
    ) {
        Note updatedNote = updateUserNoteInputBoundary.updateUserNote(
                noteId,
                NoteModelMapper.mapNoteRequestToSaveUserNoteParams(0, updateNoteRequest)
        );

        return ResponseEntity.ok(NoteModelMapper.mapNoteToNoteResponse(updatedNote));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteNote(@PathVariable("id") Long noteId) {
        removeUserNoteInputBoundary.removeUserNote(noteId, null);

        return ResponseEntity.ok(null);
    }
}

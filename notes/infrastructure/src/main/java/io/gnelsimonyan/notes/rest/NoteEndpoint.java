package io.gnelsimonyan.notes.rest;

import io.gnelsimonyan.notes.boundaries.input.CreateUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.FindUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.RemoveUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.UpdateUserNoteInputBoundary;
import io.gnelsimonyan.notes.Note;
import io.gnelsimonyan.notes.rest.dtos.CreateNoteRequest;
import io.gnelsimonyan.notes.rest.dtos.NoteDtoMapper;
import io.gnelsimonyan.notes.rest.dtos.NoteResponse;
import io.gnelsimonyan.notes.rest.dtos.UpdateNoteRequest;
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
        List<NoteResponse> noteResponseList = findUserNoteInputBoundary.findUserNotes(1L)
                .stream()
                .map(NoteDtoMapper::mapNoteToNoteResponse)
                .toList();

        return ResponseEntity.ok(noteResponseList);
    }

    @GetMapping("{id}")
    public ResponseEntity<NoteResponse> getNote(@PathVariable("id") final Long noteId) {
        Note note = findUserNoteInputBoundary.findUserNote(noteId, null);

        return ResponseEntity.ok(NoteDtoMapper.mapNoteToNoteResponse(note));
    }

    @PostMapping
    public ResponseEntity<NoteResponse> createNote(@RequestBody final CreateNoteRequest createNoteRequest) {
        Note createdNote = createUserNoteInputBoundary.createUserNote(
                NoteDtoMapper.mapNoteRequestToSaveUserNoteParams(0, createNoteRequest)
        );

        return ResponseEntity.ok(NoteDtoMapper.mapNoteToNoteResponse(createdNote));
    }

    @PutMapping("{id}")
    public ResponseEntity<NoteResponse> updateNote(
            @PathVariable("id") final long noteId,
            @RequestBody final UpdateNoteRequest updateNoteRequest
    ) {
        Note updatedNote = updateUserNoteInputBoundary.updateUserNote(
                noteId,
                NoteDtoMapper.mapNoteRequestToSaveUserNoteParams(0, updateNoteRequest)
        );

        return ResponseEntity.ok(NoteDtoMapper.mapNoteToNoteResponse(updatedNote));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteNote(@PathVariable("id") final Long noteId) {
        removeUserNoteInputBoundary.removeUserNote(noteId, null);

        return ResponseEntity.ok(null);
    }
}

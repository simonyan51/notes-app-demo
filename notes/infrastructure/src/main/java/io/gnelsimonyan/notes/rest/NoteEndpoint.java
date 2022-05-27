package io.gnelsimonyan.notes.rest;

import io.gnelsimonyan.notes.boundaries.input.CreateUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.FindUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.RemoveUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.UpdateUserNoteInputBoundary;
import io.gnelsimonyan.notes.Note;
import io.gnelsimonyan.notes.rest.dtos.CreateNoteRequest;
import io.gnelsimonyan.notes.rest.mappers.NoteDtoMapper;
import io.gnelsimonyan.notes.rest.dtos.NoteResponse;
import io.gnelsimonyan.notes.rest.dtos.UpdateNoteRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<List<NoteResponse>> getNotes(
            @AuthenticationPrincipal(expression = "id") final Long userId
    ) {

        List<NoteResponse> noteResponseList = findUserNoteInputBoundary.findUserNotes(userId)
                .stream()
                .map(NoteDtoMapper::mapNoteToNoteResponse)
                .toList();

        return ResponseEntity.ok(noteResponseList);
    }

    @GetMapping("{id}")
    public ResponseEntity<NoteResponse> getNote(
            @AuthenticationPrincipal(expression = "id") final Long userId,
            @PathVariable("id") final Long noteId
    ) {

        Note note = findUserNoteInputBoundary.findUserNote(noteId, userId);

        return ResponseEntity.ok(NoteDtoMapper.mapNoteToNoteResponse(note));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<NoteResponse> createNote(
            @AuthenticationPrincipal(expression = "id") final Long userId,
            @RequestBody
            @Valid
            final CreateNoteRequest createNoteRequest
    ) {

        Note createdNote = createUserNoteInputBoundary.createUserNote(
                NoteDtoMapper.mapNoteRequestToSaveUserNoteParams(userId, createNoteRequest)
        );

        return ResponseEntity.ok(NoteDtoMapper.mapNoteToNoteResponse(createdNote));
    }

    @PutMapping("{id}")
    public ResponseEntity<NoteResponse> updateNote(
            @AuthenticationPrincipal(expression = "id") final Long userId,
            @PathVariable("id") final long noteId,
            @RequestBody
            @Valid
            final UpdateNoteRequest updateNoteRequest
    ) {

        Note updatedNote = updateUserNoteInputBoundary.updateUserNote(
                noteId,
                NoteDtoMapper.mapNoteRequestToSaveUserNoteParams(userId, updateNoteRequest)
        );

        return ResponseEntity.ok(NoteDtoMapper.mapNoteToNoteResponse(updatedNote));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteNote(
            @AuthenticationPrincipal(expression = "id") final Long userId,
            @PathVariable("id") final Long noteId
    ) {

        removeUserNoteInputBoundary.removeUserNote(noteId, userId);

        return ResponseEntity.ok(null);
    }
}

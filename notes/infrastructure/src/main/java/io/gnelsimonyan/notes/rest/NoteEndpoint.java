package io.gnelsimonyan.notes.rest;

import io.gnelsimonyan.notes.boundaries.input.CreateUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.FindUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.RemoveUserNoteInputBoundary;
import io.gnelsimonyan.notes.boundaries.input.UpdateUserNoteInputBoundary;
import io.gnelsimonyan.notes.Note;
import io.gnelsimonyan.notes.rest.dtos.SaveNoteRequest;
import io.gnelsimonyan.notes.rest.mappers.NoteDtoMapper;
import io.gnelsimonyan.notes.rest.dtos.NoteResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("v1/notes")
@AllArgsConstructor
public class NoteEndpoint {
    private final Logger logger = LoggerFactory.getLogger(NoteEndpoint.class);

    private final FindUserNoteInputBoundary findUserNoteInputBoundary;

    private final CreateUserNoteInputBoundary createUserNoteInputBoundary;

    private final UpdateUserNoteInputBoundary updateUserNoteInputBoundary;

    private final RemoveUserNoteInputBoundary removeUserNoteInputBoundary;

    @GetMapping
    public ResponseEntity<List<NoteResponse>> getNotes(
            @AuthenticationPrincipal(expression = "id") final Long userId
    ) {
        logger.info("Fetching user-{} notes", userId);

        List<NoteResponse> noteResponseList = findUserNoteInputBoundary.findUserNotes(userId)
                .stream()
                .map(NoteDtoMapper::mapNoteToNoteResponse)
                .toList();

        logger.debug("Successfully fetched notes of user-{}", userId);
        return ResponseEntity.ok(noteResponseList);
    }

    @GetMapping("{id}")
    public ResponseEntity<NoteResponse> getNote(
            @AuthenticationPrincipal(expression = "id") final Long userId,
            @PathVariable("id") final Long noteId
    ) {
        logger.info("Fetching user-{} note by id {}", userId, noteId);

        Note note = findUserNoteInputBoundary.findUserNote(noteId, userId);

        logger.debug("Successfully fetched user-{} note by id {}", userId, noteId);
        return ResponseEntity.ok(NoteDtoMapper.mapNoteToNoteResponse(note));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<NoteResponse> createNote(
            @AuthenticationPrincipal(expression = "id") final Long userId,
            @RequestBody
            @Valid
            final SaveNoteRequest createNoteRequest
    ) {
        logger.info("Creating user-{} note", userId);

        Note createdNote = createUserNoteInputBoundary.createUserNote(
                NoteDtoMapper.mapNoteRequestToSaveUserNoteParams(userId, createNoteRequest)
        );

        logger.debug("Successfully created user-{} note", userId);
        return ResponseEntity.ok(NoteDtoMapper.mapNoteToNoteResponse(createdNote));
    }

    @PutMapping("{id}")
    public ResponseEntity<NoteResponse> updateNote(
            @AuthenticationPrincipal(expression = "id") final Long userId,
            @PathVariable("id") final long noteId,
            @RequestBody
            @Valid
            final SaveNoteRequest updateNoteRequest
    ) {
        logger.info("Updating user-{} note by id {}", userId, noteId);

        Note updatedNote = updateUserNoteInputBoundary.updateUserNote(
                noteId,
                NoteDtoMapper.mapNoteRequestToSaveUserNoteParams(userId, updateNoteRequest)
        );

        logger.debug("Successfully updated user-{} note by id {}", userId, noteId);
        return ResponseEntity.ok(NoteDtoMapper.mapNoteToNoteResponse(updatedNote));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteNote(
            @AuthenticationPrincipal(expression = "id") final Long userId,
            @PathVariable("id") final Long noteId
    ) {
        logger.info("Deleting user-{} note by id {}", userId, noteId);

        removeUserNoteInputBoundary.removeUserNote(noteId, userId);

        logger.debug("Successfully deleted user-{} note by id {}", userId, noteId);
        return ResponseEntity.ok(null);
    }
}

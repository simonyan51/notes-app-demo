package io.gnelsimonyan.notes.rest.exceptionhandlers;

import io.gnelsimonyan.notes.exceptions.InvalidParameterException;
import io.gnelsimonyan.notes.exceptions.NoteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class EndpointExceptionHandler {

    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoteNotFoundException(NoteNotFoundException exception) {
        ExceptionResponse response = ExceptionResponse
                .of(exception.getMessage())
                .putMetadata("noteId", exception.getNoteId());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidParameterException(InvalidParameterException exception) {
        ExceptionResponse response = ExceptionResponse
                .of(exception.getMessage())
                .putMetadata("field", exception.getField());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}

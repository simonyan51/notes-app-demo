/*
 * Created by Gnel Simonyan
 * Created on 25/05/2022 17:14
 *
 * Copyright (c) 2022 PicsArt.Inc
 */

package io.gnelsimonyan.notes.persistence.note;

import io.gnelsimonyan.notes.boundaries.output.FindUserNoteOutputBoundary;
import io.gnelsimonyan.notes.boundaries.output.RemoveNoteOutputBoundary;
import io.gnelsimonyan.notes.boundaries.output.SaveNoteOutputBoundary;
import io.gnelsimonyan.notes.boundaries.output.TransactionManagerOutputBoundary;
import io.gnelsimonyan.notes.note.Note;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

public class NoteOutputBoundaryAdapter implements
        FindUserNoteOutputBoundary,
        SaveNoteOutputBoundary,
        RemoveNoteOutputBoundary,
        TransactionManagerOutputBoundary {

    private final NoteRepository noteRepository;

    public NoteOutputBoundaryAdapter(final NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> findNoteByUserId(final Long userId) {
        return null;
    }

    @Override
    public Note findUserNote(final Long noteId, final Long userId) {
        return null;
    }

    @Override
    public void removeNote(final Long noteId) {

    }

    @Override
    public Note saveNote(final Note noteToBeCreated) {
        return null;
    }

    @Override
    @Transactional
    public <T> T execute(final Supplier<T> supplier) {
        return supplier.get();
    }
}

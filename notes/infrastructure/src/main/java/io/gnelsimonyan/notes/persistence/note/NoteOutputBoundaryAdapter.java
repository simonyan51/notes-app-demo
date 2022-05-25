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
import io.gnelsimonyan.notes.persistence.note.entities.NoteEntity;
import io.gnelsimonyan.notes.persistence.note.entities.NoteEntityMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

@Component
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
        return noteRepository.findAllByUserId(userId)
                .stream()
                .map(NoteEntityMapper::mapNoteEntityToNote)
                .toList();
    }

    @Override
    public Note findUserNote(final Long noteId, final Long userId) {
        NoteEntity entity = noteRepository.findByIdAndUserId(noteId, userId);
        if (entity == null) return null;

        return NoteEntityMapper.mapNoteEntityToNote(entity);
    }

    @Override
    public void removeNote(final Long noteId) {
        noteRepository.deleteById(noteId);
    }

    @Override
    public Note saveNote(final Note noteToBeCreated) {
        NoteEntity createdNote = noteRepository.save(NoteEntityMapper.mapNoteToNoteEntity(noteToBeCreated));

        return NoteEntityMapper.mapNoteEntityToNote(createdNote);
    }

    @Override
    @Transactional
    public <T> T execute(final Supplier<T> supplier) {
        return supplier.get();
    }
}

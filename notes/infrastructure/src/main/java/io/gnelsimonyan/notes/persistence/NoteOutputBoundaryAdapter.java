package io.gnelsimonyan.notes.persistence;

import io.gnelsimonyan.notes.boundaries.output.FindUserNoteOutputBoundary;
import io.gnelsimonyan.notes.boundaries.output.RemoveNoteOutputBoundary;
import io.gnelsimonyan.notes.boundaries.output.SaveNoteOutputBoundary;
import io.gnelsimonyan.notes.boundaries.output.TransactionManagerOutputBoundary;
import io.gnelsimonyan.notes.Note;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

@Component
class NoteOutputBoundaryAdapter implements
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

package io.gnelsimonyan.notes.persistence.note;

import io.gnelsimonyan.notes.persistence.note.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
}

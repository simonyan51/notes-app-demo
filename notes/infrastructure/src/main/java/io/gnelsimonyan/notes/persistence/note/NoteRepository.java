package io.gnelsimonyan.notes.persistence.note;

import io.gnelsimonyan.notes.persistence.note.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface NoteRepository extends JpaRepository<NoteEntity, Long> {
    List<NoteEntity> findAllByUserId(Long userId);

    NoteEntity findByIdAndUserId(Long noteId, Long userId);
}

package io.gnelsimonyan.notes.persistence;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notes", indexes = {
        @Index(columnList = "user_id", name = "idx_note_user_id")
})
@Data
@NoArgsConstructor
@Accessors(fluent = true)
@AllArgsConstructor(staticName = "of")
class NoteEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

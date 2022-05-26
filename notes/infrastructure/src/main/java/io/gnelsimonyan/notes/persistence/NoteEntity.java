package io.gnelsimonyan.notes.persistence;

import io.gnelsimonyan.notes.persistence.common.AbstractEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notes", indexes = {
        @Index(columnList = "user_id", name = "idx_note_user_id")
})
class NoteEntity extends AbstractEntity {

    @Column(name="title", nullable = false)
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    static NoteEntity of(
            final Long id,
            final String title,
            final String text,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt,
            final Long userId
    ) {
        return new NoteEntity(
                id,
                title,
                text,
                createdAt,
                updatedAt,
                userId
        );
    }

    public NoteEntity() {
        super();
    }

    public NoteEntity(
            final Long id,
            final String title,
            final String text,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt,
            final Long userId
    ) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.text = text;
        this.userId = userId;
    }

    public String title() {
        return title;
    }

    public String text() {
        return text;
    }

    public Long userId() {
        return userId;
    }

}

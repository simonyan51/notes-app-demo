/*
 * Created by Gnel Simonyan
 * Created on 25/05/2022 17:12
 *
 * Copyright (c) 2022 PicsArt.Inc
 */

package io.gnelsimonyan.notes.persistence.note.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.gnelsimonyan.notes.persistence.common.AbstractEntity;
import io.gnelsimonyan.notes.persistence.user.entities.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "note")
public class NoteEntity extends AbstractEntity {

    @Column(nullable = false)
    private String title;

    @Column()
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity user;

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
        this.user = new UserEntity(userId, null, null, null, null);
    }

    public String title() {
        return title;
    }

    public String text() {
        return text;
    }

    public UserEntity user() {
        return user;
    }

}

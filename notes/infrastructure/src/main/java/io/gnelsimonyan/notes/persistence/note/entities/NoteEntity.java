/*
 * Created by Gnel Simonyan
 * Created on 25/05/2022 17:12
 *
 * Copyright (c) 2022 PicsArt.Inc
 */

package io.gnelsimonyan.notes.persistence.note.entities;

import io.gnelsimonyan.notes.persistence.common.AbstractEntity;
import io.gnelsimonyan.notes.persistence.user.entities.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "note")
public class NoteEntity extends AbstractEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column()
    private String text;

    @ManyToOne
    private UserEntity user;

    public NoteEntity() {
        super();
    }

    public NoteEntity(
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt,
            final Long id,
            final String title,
            final String text
    ) {
        super(createdAt, updatedAt);
        this.id = id;
        this.title = title;
        this.text = text;
    }
}

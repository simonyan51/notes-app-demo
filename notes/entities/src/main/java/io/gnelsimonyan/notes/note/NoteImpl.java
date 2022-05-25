/*
 * Created by Gnel Simonyan
 * Created on 25/05/2022 10:41
 *
 * Copyright (c) 2022 PicsArt.Inc
 */

package io.gnelsimonyan.notes.note;

import io.gnelsimonyan.notes.common.AbstractDomainEntity;

import java.time.LocalDateTime;

final class NoteImpl extends AbstractDomainEntity implements Note {

    private final Long userId;

    private String title;

    private String text;

    NoteImpl(
            final Long userId,
            final String title,
            final String text
    ) {
        super();
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    NoteImpl(
            final Long id,
            final Long userId,
            final String title,
            final String text,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ) {
        super(id, createdAt, updatedAt);
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public Long userId() {
        return userId;
    }

    public String title() {
        return title;
    }

    public String text() {
        return text;
    }

    public void changeTitle(final String title) {
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }

    public void changeText(final String text) {
        this.text = text;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Note {" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", note='" + text + '\'' +
                '}';
    }
}

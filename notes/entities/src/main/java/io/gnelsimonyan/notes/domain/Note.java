/*
 * Created by Gnel Simonyan
 * Created on 25/05/2022 10:41
 *
 * Copyright (c) 2022 PicsArt.Inc
 */

package io.gnelsimonyan.notes.domain;

import io.gnelsimonyan.notes.common.AbstractDomain;

import java.time.LocalDateTime;

public final class Note extends AbstractDomain {

    private final Long userId;

    private String title;

    private String note;


    public Note(Long userId, String title, String note) {
        super();
        this.userId = userId;
        this.title = title;
        this.note = note;
    }

    public Note(
            Long id,
            Long userId,
            String title,
            String note,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        super(id, createdAt, updatedAt);
        this.userId = userId;
        this.title = title;
        this.note = note;
    }

    public Long userId() {
        return userId;
    }

    public String title() {
        return title;
    }

    public String note() {
        return note;
    }

    public void changeTitle(String title) {
        this.title = title;
        this.updatedAt = LocalDateTime.now();
    }

    public void changeNote(String note) {
        this.note = note;
        this.updatedAt = LocalDateTime.now();
    }

}

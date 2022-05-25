/*
 * Created by Gnel Simonyan
 * Created on 25/05/2022 17:12
 *
 * Copyright (c) 2022 PicsArt.Inc
 */

package io.gnelsimonyan.notes.persistence.note.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "note")
public class NoteEntity {
    @Id
    @GeneratedValue
    private Long id;


}

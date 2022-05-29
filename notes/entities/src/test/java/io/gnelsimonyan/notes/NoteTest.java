package io.gnelsimonyan.notes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteTest {
    Note note;

    @BeforeEach
    void init() {
        note = Note.of(1L, "title", "text");
    }

    @Test
    void whenChangingTitle_thenUpdatedDateShouldBeUpdated() {
        note.changeTitle("title1");

        assertEquals(note.title(), "title1");
        assertNotNull(note.updatedAt());
    }

    @Test
    void whenChangingText_thenUpdatedDateShouldBeUpdated() {
        note.changeText("text1");

        assertEquals(note.text(), "text1");
        assertNotNull(note.updatedAt());
    }
}

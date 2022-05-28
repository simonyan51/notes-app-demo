package io.gnelsimonyan.notes.boundaries.input.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@AllArgsConstructor(staticName = "of")
public class SaveUserNoteParams {
    private static final int TITLE_MAX_LENGTH = 50;
    private static final int TEXT_MAX_LENGTH = 1000;

    private Long userId;

    private String title;

    private String text;

    public void validateParams() {
        if (userId == null) {
            throw new IllegalArgumentException("userId must be provided");
        }

        if (title == null || title.isEmpty() || title.length() > TITLE_MAX_LENGTH) {
            throw new IllegalArgumentException(
                    "title must be provided and length must not exceed " + TITLE_MAX_LENGTH
            );
        }

        if (text != null && text.length() > TEXT_MAX_LENGTH) {
            throw new IllegalArgumentException(
                    "text length must not exceed " + TITLE_MAX_LENGTH
            );
        }
    }
}

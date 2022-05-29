package io.gnelsimonyan.notes.boundaries.input.params;

import io.gnelsimonyan.notes.exceptions.InvalidParameterException;
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

    public void validateParams() throws InvalidParameterException {
        if (userId == null) {
            throw new InvalidParameterException("User Id must be provided", "userId");
        }

        if (title == null || title.isEmpty() || title.length() > TITLE_MAX_LENGTH) {
            throw new InvalidParameterException(
                    "title must be provided and length must not exceed " + TITLE_MAX_LENGTH,
                    "title"
            );
        }

        if (text != null && text.length() > TEXT_MAX_LENGTH) {
            throw new InvalidParameterException(
                    "text length must not exceed " + TITLE_MAX_LENGTH,
                    "text"
            );
        }
    }
}

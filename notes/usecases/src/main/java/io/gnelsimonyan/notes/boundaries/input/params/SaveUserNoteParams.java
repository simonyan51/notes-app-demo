package io.gnelsimonyan.notes.boundaries.input.params;

public interface SaveUserNoteParams {

    Long userId();

    String title();

    String text();

    void validateParams();

    static SaveUserNoteParams of(final Long userId, final String title, final String text) {
        return new SaveUserNoteParamsImpl(userId, title, text);
    }

    record SaveUserNoteParamsImpl(Long userId, String title, String text) implements SaveUserNoteParams {
        private static final int TITLE_MAX_LENGTH = 50;
        private static final int TEXT_MAX_LENGTH = 1000;

        @Override
        public void validateParams() {
            if (userId == null) {
                throw new IllegalArgumentException("userId must be provided");
            }

            if (title == null || title.isEmpty() || title.length() > SaveUserNoteParamsImpl.TITLE_MAX_LENGTH) {
                throw new IllegalArgumentException(
                        "title must be provided and length must not exceed " + SaveUserNoteParamsImpl.TITLE_MAX_LENGTH
                );
            }

            if (text != null && text.length() > SaveUserNoteParamsImpl.TEXT_MAX_LENGTH) {
                throw new IllegalArgumentException(
                        "text length must not exceed " + SaveUserNoteParamsImpl.TITLE_MAX_LENGTH
                );
            }
        }
    }
}

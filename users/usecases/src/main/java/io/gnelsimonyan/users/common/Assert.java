package io.gnelsimonyan.users.common;

public interface Assert {

    static void notNull(final Object value, final String message) {
        if (value == null) throw new IllegalArgumentException(message);
    }

    static void notEmpty(final String value, final String message) {
        if (value == null || value.isEmpty()) throw new IllegalArgumentException(message);
    }
}

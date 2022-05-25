package io.gnelsimonyan.notes.boundaries.output;

import java.util.function.Supplier;

public interface TransactionManagerOutputBoundary {
    <T> T execute(Supplier<T> supplier);
}

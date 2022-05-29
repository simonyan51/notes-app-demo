package io.gnelsimonyan.notes.boundaries.output;

import java.util.function.Supplier;

public interface TransactionManagerOutputBoundary {
    <T, E extends Exception> T execute(TransactionManagerSupplier<T, E> supplier) throws E;
}

package io.gnelsimonyan.notes.boundaries.output;

import java.util.function.Supplier;

@FunctionalInterface
public interface TransactionManagerSupplier<T, E extends Exception> {
    T get() throws E;
}

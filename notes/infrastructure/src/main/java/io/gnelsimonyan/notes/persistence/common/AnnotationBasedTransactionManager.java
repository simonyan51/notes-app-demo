package io.gnelsimonyan.notes.persistence.common;

import io.gnelsimonyan.notes.boundaries.output.TransactionManagerOutputBoundary;
import io.gnelsimonyan.notes.common.Assert;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Transactional
@Component
public class AnnotationBasedTransactionManager implements TransactionManagerOutputBoundary {
    @Override
    public <T> T execute(Supplier<T> supplier) {
        Assert.notNull(supplier, "Supplier must be provided");
        return supplier.get();
    }
}

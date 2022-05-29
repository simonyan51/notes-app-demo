package io.gnelsimonyan.notes.persistence.common;

import io.gnelsimonyan.notes.boundaries.output.TransactionManagerOutputBoundary;
import io.gnelsimonyan.notes.boundaries.output.TransactionManagerSupplier;
import io.gnelsimonyan.notes.common.Assert;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class AnnotationBasedTransactionManager implements TransactionManagerOutputBoundary {

    @Override
    public <T, E extends Exception> T execute(TransactionManagerSupplier<T, E> supplier) throws E {
        Assert.notNull(supplier, "Supplier must be provided");

        return supplier.get();
    }
}

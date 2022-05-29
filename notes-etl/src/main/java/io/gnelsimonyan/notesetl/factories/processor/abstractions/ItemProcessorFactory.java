package io.gnelsimonyan.notesetl.factories.processor.abstractions;

import io.gnelsimonyan.notesetl.factories.common.ProcessorType;
import org.springframework.batch.item.ItemProcessor;

public interface ItemProcessorFactory {
    <I, O> ItemProcessor<I, O> getItemProcessor(ProcessorType processorType);
}

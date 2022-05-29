package io.gnelsimonyan.notesetl.factories.processor;

import io.gnelsimonyan.notesetl.factories.common.ProcessorType;
import io.gnelsimonyan.notesetl.factories.processor.abstractions.ItemProcessorFactory;
import io.gnelsimonyan.notesetl.processors.json.NoteEntityToJSONProcessor;
import io.gnelsimonyan.notesetl.processors.parquet.NoteEntityToNoteSchemaProcessor;
import org.springframework.batch.item.ItemProcessor;

public class NotesEntityItemProcessorFactory implements ItemProcessorFactory {
    @Override
    public ItemProcessor getItemProcessor(ProcessorType processorType) {
        return switch (processorType) {
            case JSON -> new NoteEntityToJSONProcessor();
            case PARQUET -> new NoteEntityToNoteSchemaProcessor();
        };
    }
}

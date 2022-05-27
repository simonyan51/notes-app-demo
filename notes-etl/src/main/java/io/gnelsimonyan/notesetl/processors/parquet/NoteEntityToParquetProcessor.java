package io.gnelsimonyan.notesetl.processors.parquet;

import io.gnelsimonyan.notesetl.source.NoteEntity;
import org.springframework.batch.item.ItemProcessor;

public class NoteEntityToParquetProcessor implements ItemProcessor<NoteEntity, String> {
    @Override
    public String process(NoteEntity noteEntity) throws Exception {
        return null;
    }
}

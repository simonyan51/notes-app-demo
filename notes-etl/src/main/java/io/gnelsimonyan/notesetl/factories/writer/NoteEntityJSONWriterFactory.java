package io.gnelsimonyan.notesetl.factories.writer;

import io.gnelsimonyan.notesetl.factories.common.WriterType;
import io.gnelsimonyan.notesetl.factories.writer.abstractions.ItemWriterFactory;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.core.io.FileSystemResource;

public class NoteEntityJSONWriterFactory implements ItemWriterFactory {

    @Override
    public ItemWriter getItemWriter(final WriterType writerType) {
        return switch (writerType) {
            case FILE -> new FlatFileItemWriterBuilder<JSONObject>()
                    .name("fileWriter")
                    .resource(new FileSystemResource("resources/notes-" + System.currentTimeMillis() + ".json"))
                    .lineAggregator(new PassThroughLineAggregator<>())
                    .build();
            case S3 -> null;
        };
    }
}

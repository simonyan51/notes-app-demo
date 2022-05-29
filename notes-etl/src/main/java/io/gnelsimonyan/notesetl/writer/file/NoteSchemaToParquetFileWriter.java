package io.gnelsimonyan.notesetl.writer.file;

import io.gnelsimonyan.notesetl.processors.parquet.NoteSchema;
import org.apache.parquet.hadoop.ParquetWriter;

import java.io.IOException;
import java.util.List;

public class NoteSchemaToParquetFileWriter implements NoteSchemaToParquetWriter {
    private final ParquetWriter<NoteSchema> parquetWriter;

    public NoteSchemaToParquetFileWriter(ParquetWriter<NoteSchema> parquetWriter) {
        this.parquetWriter = parquetWriter;
    }

    @Override
    public void write(List<? extends NoteSchema> list) {
        list.forEach(schema -> {
            try {
                parquetWriter.write(schema);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

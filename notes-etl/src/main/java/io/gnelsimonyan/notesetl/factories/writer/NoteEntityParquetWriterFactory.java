package io.gnelsimonyan.notesetl.factories.writer;

import io.gnelsimonyan.notesetl.factories.common.WriterType;
import io.gnelsimonyan.notesetl.factories.writer.abstractions.ItemWriterFactory;
import io.gnelsimonyan.notesetl.processors.parquet.NoteSchema;
import io.gnelsimonyan.notesetl.writer.file.NoteSchemaToParquetFileWriter;
import org.apache.avro.reflect.ReflectData;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.springframework.batch.item.ItemWriter;

import java.io.IOException;

import static org.apache.parquet.hadoop.ParquetFileWriter.Mode.OVERWRITE;
import static org.apache.parquet.hadoop.metadata.CompressionCodecName.SNAPPY;

public class NoteEntityParquetWriterFactory implements ItemWriterFactory {

    @Override
    public ItemWriter getItemWriter(WriterType writerType) throws IOException {
        return switch (writerType) {
            case FILE -> createParquetFileWriter();
            case S3 -> null;
        };
    }

    private static NoteSchemaToParquetFileWriter createParquetFileWriter() throws IOException {
        Path dataFile = new Path("resources/notes.parquet");

        ParquetWriter<NoteSchema> parquetWriter = AvroParquetWriter.<NoteSchema>builder(dataFile)
                .withSchema(ReflectData.AllowNull.get().getSchema(NoteSchema.class))
                .withDataModel(ReflectData.get())
                .withConf(new org.apache.hadoop.conf.Configuration())
                .withCompressionCodec(SNAPPY)
                .withWriteMode(OVERWRITE)
                .build();

        return new NoteSchemaToParquetFileWriter(parquetWriter);
    }

}

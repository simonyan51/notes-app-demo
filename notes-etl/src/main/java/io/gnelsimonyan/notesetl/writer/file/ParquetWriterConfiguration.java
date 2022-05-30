package io.gnelsimonyan.notesetl.writer.file;

import io.gnelsimonyan.notesetl.processors.parquet.NoteSchema;
import org.apache.avro.reflect.ReflectData;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

import static org.apache.parquet.hadoop.ParquetFileWriter.Mode.OVERWRITE;
import static org.apache.parquet.hadoop.metadata.CompressionCodecName.SNAPPY;

@Configuration
public class ParquetWriterConfiguration {
    @Bean
    NoteSchemaToParquetWriter noteSchemaToParquetWriter(ParquetWriter<NoteSchema> parquetWriter) {
        return new NoteSchemaToParquetWriterImpl(parquetWriter);
    }

    @Bean
    public ParquetWriter<NoteSchema> parquetWriter() throws IOException {
        Path dataFile = new Path("resources/notes.parquet");

        return AvroParquetWriter.<NoteSchema>builder(dataFile)
                .withSchema(ReflectData.AllowNull.get().getSchema(NoteSchema.class))
                .withDataModel(ReflectData.get())
                .withConf(new org.apache.hadoop.conf.Configuration())
                .withCompressionCodec(SNAPPY)
                .withWriteMode(OVERWRITE)
                .build();
    }
}

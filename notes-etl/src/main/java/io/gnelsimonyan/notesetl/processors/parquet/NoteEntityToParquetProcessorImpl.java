package io.gnelsimonyan.notesetl.processors.parquet;

import io.gnelsimonyan.notesetl.source.NoteEntity;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

class NoteEntityToParquetProcessorImpl implements NoteEntityToParquetProcessor {
    @Override
    public String process(NoteEntity noteEntity) throws Exception {
        SparkSession spark = SparkSession
                .builder()
                .master("local")
                .appName("notesConvertor")
                .getOrCreate();

        return null;
    }
}

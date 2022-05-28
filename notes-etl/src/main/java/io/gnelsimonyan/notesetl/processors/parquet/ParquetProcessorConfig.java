package io.gnelsimonyan.notesetl.processors.parquet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParquetProcessorConfig {
    @Bean
    NoteEntityToParquetProcessor noteEntityToParquetProcessor() {
        return new NoteEntityToParquetProcessorImpl();
    }
}

package io.gnelsimonyan.notesetl.processors.parquet;

import org.springframework.context.annotation.Bean;

public class ParquetProcessorConfiguration {

    @Bean
    ParquetProcessorConfiguration parquetProcessorConfiguration() {
        return new ParquetProcessorConfiguration();
    }
}

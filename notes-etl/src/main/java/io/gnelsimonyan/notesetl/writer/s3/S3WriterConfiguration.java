package io.gnelsimonyan.notesetl.writer.s3;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3WriterConfiguration {
    @Bean
    ItemWriter s3ItemWriter() {
        return new S3Writer();
    }
}

package io.gnelsimonyan.notesetl.configs;

import io.gnelsimonyan.notesetl.processors.json.NoteEntityToJSONProcessor;
import io.gnelsimonyan.notesetl.processors.json.NoteEntityToJSONProcessorImpl;
import io.gnelsimonyan.notesetl.source.NoteEntity;
import io.gnelsimonyan.notesetl.source.NoteRepository;
import lombok.AllArgsConstructor;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableBatchProcessing
@EnableJpaRepositories
@AllArgsConstructor
public class BatchProcessorConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final NoteRepository noteRepository;

    @Bean
    RepositoryItemReader<NoteEntity> repositoryItemReader() {
        return new RepositoryItemReaderBuilder<NoteEntity>()
                .repository(noteRepository)
                .methodName("findAll")
                .build();
    }

    @Bean
    NoteEntityToJSONProcessor noteEntityToJSONProcessor() {
        return new NoteEntityToJSONProcessorImpl();
    }
//
//    @Bean
//    ItemWriter<>


//    @Bean
//    Step jsonStep() {
//        return stepBuilderFactory
//                .get("jsonStep")
//                .chunk(10)
//                .reader(repositoryItemReader())
//                .<NoteEntity, JSONObject>processor(noteEntityToJSONProcessor())
//                .writer(null)
//                .build();
//    }

}

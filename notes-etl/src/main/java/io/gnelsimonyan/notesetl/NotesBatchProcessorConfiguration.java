package io.gnelsimonyan.notesetl;

import io.gnelsimonyan.notesetl.source.NoteEntity;
import io.gnelsimonyan.notesetl.source.NoteRepository;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.util.Collections;

@Configuration
@EnableBatchProcessing
@EnableJpaRepositories
public class NotesBatchProcessorConfiguration {
    private final int chunkSize;

    private final int pageSize;

    private final NoteRepository noteRepository;

    private final StepBuilderFactory stepBuilderFactory;

    private final ParameterizedJobExecutor parameterizedJobExecutor;

    public NotesBatchProcessorConfiguration(
            @Value("${batch.chunk:10}")
            final int chunkSize,
            @Value("${batch.pageSize:1}")
            final int pageSize,
            final NoteRepository noteRepository,
            final StepBuilderFactory stepBuilderFactory,
            final ParameterizedJobExecutor parameterizedJobExecutor
    ) {
        this.chunkSize = chunkSize;
        this.pageSize = pageSize;
        this.noteRepository = noteRepository;
        this.stepBuilderFactory = stepBuilderFactory;
        this.parameterizedJobExecutor = parameterizedJobExecutor;
    }


    @Bean
    RepositoryItemReader<NoteEntity> repositoryItemReader() {
        return new RepositoryItemReaderBuilder<NoteEntity>()
                .name("notesRead")
                .repository(noteRepository)
                .pageSize(pageSize)
                .sorts(Collections.singletonMap("id", Sort.Direction.ASC))
                .methodName("findAll")
                .build();
    }

    @Bean
    Step migrationStep() {

        return stepBuilderFactory
                .get("notesMigrationStep")
                .<NoteEntity, JSONObject>chunk(this.chunkSize)
                .reader(repositoryItemReader())
                .processor(parameterizedJobExecutor)
                .writer(parameterizedJobExecutor)
                .build();
    }

    @Bean
    Job notesMigrationJob(JobRepository jobRepository, Step migrationStep) throws IOException {

        return new JobBuilderFactory(jobRepository)
                .get("notesProcessorJob")
                .incrementer(new RunIdIncrementer())
                .flow(migrationStep)
                .end()
                .build();


    }
}

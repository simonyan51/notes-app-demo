package io.gnelsimonyan.notesetl;

import io.gnelsimonyan.notesetl.processors.json.NoteEntityToJSONProcessor;
import io.gnelsimonyan.notesetl.processors.parquet.NoteEntityToNoteSchemaProcessor;
import io.gnelsimonyan.notesetl.processors.parquet.NoteSchema;
import io.gnelsimonyan.notesetl.source.NoteEntity;
import io.gnelsimonyan.notesetl.source.NoteRepository;
import io.gnelsimonyan.notesetl.writer.file.NoteSchemaToParquetWriter;
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
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Collections;

@Configuration
@EnableBatchProcessing
@EnableJpaRepositories
public class NotesBatchProcessorConfiguration {
    private final int chunkSize;

    private final int pageSize;

    private final NoteRepository noteRepository;

    private final StepBuilderFactory stepBuilderFactory;

    private final NoteSchemaToParquetWriter noteSchemaToParquetWriter;

    private final NoteEntityToNoteSchemaProcessor noteEntityToNoteSchemaProcessor;

    private final NoteEntityToJSONProcessor noteEntityToJSONProcessor;

    public NotesBatchProcessorConfiguration(
            @Value("${batch.chunk:10}")
            final int chunkSize,
            @Value("${batch.pageSize:1}")
            final int pageSize,
            final NoteRepository noteRepository,
            final StepBuilderFactory stepBuilderFactory,
            final NoteSchemaToParquetWriter noteSchemaToParquetWriter,
            final NoteEntityToNoteSchemaProcessor noteEntityToNoteSchemaProcessor,
            final NoteEntityToJSONProcessor noteEntityToJSONProcessor
    ) {
        this.chunkSize = chunkSize;
        this.pageSize = pageSize;
        this.noteRepository = noteRepository;
        this.stepBuilderFactory = stepBuilderFactory;
        this.noteSchemaToParquetWriter = noteSchemaToParquetWriter;
        this.noteEntityToNoteSchemaProcessor = noteEntityToNoteSchemaProcessor;
        this.noteEntityToJSONProcessor = noteEntityToJSONProcessor;
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
    FlatFileItemWriter<JSONObject> flatFileItemWriter() {
        return new FlatFileItemWriterBuilder<JSONObject>()
                .name("fileWriter")
                .resource(new FileSystemResource("resources/notes-" + System.currentTimeMillis() + ".json"))
                .lineAggregator(new PassThroughLineAggregator<>())
                .build();
    }

    @Bean
    Step parquetStep() {
        return stepBuilderFactory
                .get("parquetStep")
                .<NoteEntity, NoteSchema>chunk(this.chunkSize)
                .reader(repositoryItemReader())
                .processor(noteEntityToNoteSchemaProcessor)
                .writer(noteSchemaToParquetWriter)
                .build();
    }

    @Bean
    Step jsonStep() {
        return stepBuilderFactory
                .get("jsonStep")
                .<NoteEntity, JSONObject>chunk(this.chunkSize)
                .reader(repositoryItemReader())
                .processor(noteEntityToJSONProcessor)
                .writer(flatFileItemWriter())
                .build();
    }

    @Bean
    Job notesMigrationJob(JobRepository jobRepository) {

        return new JobBuilderFactory(jobRepository)
                .get("notesProcessorJob")
                .incrementer(new RunIdIncrementer())
                .flow(parquetStep())
                .next(jsonStep())
                .end()
                .build();
    }
}

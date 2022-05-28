package io.gnelsimonyan.notesetl;

import io.gnelsimonyan.notesetl.processors.json.NoteEntityToJSONProcessor;
import io.gnelsimonyan.notesetl.source.NoteEntity;
import io.gnelsimonyan.notesetl.source.NoteRepository;
import io.gnelsimonyan.notesetl.writer.file.JSONObjectToParquetWriter;
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
public class BatchProcessorConfig {
    private final int chunkSize;
    private final int pageSize;
    private final NoteRepository noteRepository;
    private final StepBuilderFactory stepBuilderFactory;

    public BatchProcessorConfig(
            @Value("${batch.chunk:10}")
            final int chunkSize,
            @Value("${batch.pageSize:1}")
            final int pageSize,
            final NoteRepository noteRepository,
            final StepBuilderFactory stepBuilderFactory
    ) {
        this.chunkSize = chunkSize;
        this.pageSize = pageSize;
        this.noteRepository = noteRepository;
        this.stepBuilderFactory = stepBuilderFactory;
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
    NoteEntityToJSONProcessor noteEntityToJSONProcessor() {
        return new NoteEntityToJSONProcessor();
    }

    @Bean
    FlatFileItemWriter<JSONObject> flatFileItemWriter() {
        return new FlatFileItemWriterBuilder<JSONObject>()
                .name("fileWriter")
                .resource(new FileSystemResource("resources/notes-" + System.currentTimeMillis() + ".json"))
                .lineAggregator(new PassThroughLineAggregator<>())
                .build();
    }

    JSONObjectToParquetWriter jsonObjectToParquetWriter() {
        return new JSONObjectToParquetWriter();
    }

    @Bean
    Step jsonStep() {
        return stepBuilderFactory
                .get("notesToJsonStep")
                .<NoteEntity, JSONObject>chunk(this.chunkSize)
                .reader(repositoryItemReader())
                .processor(noteEntityToJSONProcessor())
                .writer(flatFileItemWriter())
                .build();
    }

    @Bean
    Step parquetStep() {
        return stepBuilderFactory
                .get("notesToParquetStep")
                .<NoteEntity, JSONObject>chunk(this.chunkSize)
                .reader(repositoryItemReader())
                .processor(noteEntityToJSONProcessor())
                .writer(jsonObjectToParquetWriter())
                .build();
    }

    @Bean
    Job notesMigrationJob(JobRepository jobRepository) {
        return new JobBuilderFactory(jobRepository)
                .get("notesProcessorJob")
                .incrementer(new RunIdIncrementer())
                .flow(parquetStep())
                .end()
                .build();

    }

//    @Bean
//    Step parquetStep(
//            RepositoryItemReader<NoteEntity> repositoryItemReader,
//            NoteEntityToParquetProcessor noteEntityToParquetProcessor,
//            StepBuilderFactory stepBuilderFactory
//    ) {
//        return stepBuilderFactory
//                .get("parquetStep")
//                .<NoteEntity, JSONObject>chunk(this.chunkSize)
//                .reader(repositoryItemReader)
//                .processor(noteEntityToParquetProcessor)
//                .writer(null)
//                .build();
//    }


}

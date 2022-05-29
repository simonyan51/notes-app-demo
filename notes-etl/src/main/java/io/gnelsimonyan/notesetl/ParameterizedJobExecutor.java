package io.gnelsimonyan.notesetl;

import io.gnelsimonyan.notesetl.factories.common.EntityType;
import io.gnelsimonyan.notesetl.factories.common.ProcessorType;
import io.gnelsimonyan.notesetl.factories.common.WriterType;
import io.gnelsimonyan.notesetl.factories.processor.EntityItemProcessorProvider;
import io.gnelsimonyan.notesetl.factories.writer.EntityWriteFactoryProvider;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@StepScope
@Component
public class ParameterizedJobExecutor implements ItemProcessor, ItemWriter {
    String entityType;

    String processorType;

    String writerType;

    @Override
    public Object process(Object o) throws Exception {
        return EntityItemProcessorProvider
                .getEntityItemProcessorFactory(EntityType.valueOf(entityType))
                .getItemProcessor(ProcessorType.valueOf(processorType))
                .process(o);
    }

    @Override
    public void write(List list) throws Exception {
        EntityWriteFactoryProvider.getEntityWriteFactory(EntityType.valueOf(entityType))
                .getItemWriterFactory(ProcessorType.valueOf(processorType))
                .getItemWriter(WriterType.valueOf(writerType))
                .write(list);
    }

    @Value("#{jobParameters['entityType']}")
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    @Value("#{jobParameters['processorType']}")
    public void setProcessorType(String processorType) {
        this.processorType = processorType;
    }

    @Value("#{jobParameters['writerType']}")
    public void setWriterType(String writerType) {
        this.writerType = writerType;
    }
}

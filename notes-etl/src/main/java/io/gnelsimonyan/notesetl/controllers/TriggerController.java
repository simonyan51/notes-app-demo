package io.gnelsimonyan.notesetl.controllers;

import io.gnelsimonyan.notesetl.factories.common.EntityType;
import io.gnelsimonyan.notesetl.factories.common.ProcessorType;
import io.gnelsimonyan.notesetl.factories.common.WriterType;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("execute")
public class TriggerController {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @GetMapping
    public void execute() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("entityType", EntityType.NOTES.toString())
                .addString("processorType", ProcessorType.JSON.toString())
                .addString("writerType", WriterType.FILE.toString())
                .toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

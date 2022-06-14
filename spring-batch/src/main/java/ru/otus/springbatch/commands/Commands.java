package ru.otus.springbatch.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@RequiredArgsConstructor
@ShellComponent
public class Commands {

    private final JobExplorer jobExplorer;
    private final JobLauncher jobLauncher;
    private final Job startJob;
    @Value("${app.output-file}")
    private String outputFile;

    @ShellMethod(value = "showInfo", key = "i")
    public void showInfo() {
        System.out.println(jobExplorer.getJobNames());
    }


    @ShellMethod(value = "startMigrationJobWithJobLauncher", key = "s")
    public void startMigration() throws Exception {
        JobExecution execution = jobLauncher.run(startJob, new JobParametersBuilder()
                .addString("outputFileName", outputFile)
                .toJobParameters());
        System.out.println(execution);
    }

}

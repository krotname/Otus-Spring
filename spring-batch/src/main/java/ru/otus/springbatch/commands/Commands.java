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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequiredArgsConstructor
@ShellComponent
public class Commands {

    @PersistenceContext
    EntityManager entityManager;

    private final JobExplorer jobExplorer;
    private final JobLauncher jobLauncher;
    private final Job startJob;

    @ShellMethod(value = "showInfo", key = "i")
    public void showInfo() {
        System.out.println(entityManager.getProperties());
        System.out.println(jobExplorer.getJobNames());
    }

    @ShellMethod(value = "startMigrationJob", key = "s")
    public void startMigration() throws Exception {
        JobExecution execution = jobLauncher.run(startJob, new JobParametersBuilder()
                .toJobParameters());
        System.out.println(execution);
    }

}

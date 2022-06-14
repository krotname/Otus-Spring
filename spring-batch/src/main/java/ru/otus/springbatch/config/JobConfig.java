package ru.otus.springbatch.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.lang.NonNull;
import ru.otus.springbatch.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class JobConfig {

    private static final int CHUNK_SIZE = 5;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @PersistenceContext
    EntityManager entityManager;

    @Value("${app.output-authors}")
    private String outputAuthors;

    @Bean
    public Job startJob(Step exportStep) {
        return jobBuilderFactory.get("startJob")
                .incrementer(new RunIdIncrementer())
                .flow(exportStep)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(@NonNull JobExecution jobExecution) {
                        log.info("Начало job");
                    }

                    @Override
                    public void afterJob(@NonNull JobExecution jobExecution) {
                        log.info("Конец job");
                    }
                })
                .build();
    }

    @Bean
    public Step exportStep(ItemReader<Author> reader, FlatFileItemWriter<Author> writer,
                           ItemProcessor<Author, Author> itemProcessor) {
        return stepBuilderFactory.get("step1")
                .<Author, Author>chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Author, Author> processor() {
        return (x) -> x;
    }

    @StepScope
    @Bean
    public JpaCursorItemReader<Author> reader() throws Exception {

        String jpqlQuery = "from Author";
        JpaCursorItemReader<Author> itemReader = new JpaCursorItemReader<>();
        itemReader.setQueryString(jpqlQuery);
        itemReader.setEntityManagerFactory(entityManager.getEntityManagerFactory());
        itemReader.afterPropertiesSet();
        itemReader.setSaveState(true);
        return itemReader;
    }

    @StepScope
    @Bean
    public FlatFileItemWriter<Author> writer() {
        return new FlatFileItemWriterBuilder<Author>()
                .name("authorItemWriter")
                .resource(new FileSystemResource(outputAuthors))
                .lineAggregator(new DelimitedLineAggregator<>())
                .build();
    }


}

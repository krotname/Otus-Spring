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
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import ru.otus.springbatch.entity.Author;
import ru.otus.springbatch.entity.Book;
import ru.otus.springbatch.entity.Comment;
import ru.otus.springbatch.entity.Genre;

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

    @Value("${app.output-genres}")
    private String outputGenres;

    @Value("${app.output-books}")
    private String outputBooks;

    @Value("${app.output-comments}")
    private String outputComments;

    @Bean
    public Job startJob(Step exportAuthorsStep, Step exportGenresStep, Step exportBooksStep, Step exportCommentsStep) {
        return jobBuilderFactory.get("startJob")
                .incrementer(new RunIdIncrementer())
                .flow(exportAuthorsStep)
                .next(exportGenresStep)
                .next(exportBooksStep)
                .next(exportCommentsStep)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        log.info("Начало job");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        log.info("Конец job");
                    }
                })
                .build();
    }

    @Bean
    public Step exportAuthorsStep(ItemReader<Author> reader, FlatFileItemWriter<Author> writer) {
        return stepBuilderFactory.get("step1")
                .<Author, Author>chunk(CHUNK_SIZE)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    public Step exportGenresStep(ItemReader<Genre> reader, FlatFileItemWriter<Genre> writer) {
        return stepBuilderFactory.get("step2")
                .<Genre, Genre>chunk(CHUNK_SIZE)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    public Step exportBooksStep(ItemReader<Book> reader, FlatFileItemWriter<Book> writer) {
        return stepBuilderFactory.get("step3")
                .<Book, Book>chunk(CHUNK_SIZE)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    public Step exportCommentsStep(ItemReader<Comment> reader, FlatFileItemWriter<Comment> writer) {
        return stepBuilderFactory.get("step4")
                .<Comment, Comment>chunk(CHUNK_SIZE)
                .reader(reader)
                .writer(writer)
                .build();
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

    @StepScope
    @Bean
    public JpaCursorItemReader<Genre> genreReader() throws Exception {

        String jpqlQuery = "from Genre";
        JpaCursorItemReader<Genre> itemReader = new JpaCursorItemReader<>();
        itemReader.setQueryString(jpqlQuery);
        itemReader.setEntityManagerFactory(entityManager.getEntityManagerFactory());
        itemReader.afterPropertiesSet();
        itemReader.setSaveState(true);
        return itemReader;
    }

    @StepScope
    @Bean
    public FlatFileItemWriter<Genre> genreWriter() {
        return new FlatFileItemWriterBuilder<Genre>()
                .name("genreItemWriter")
                .resource(new FileSystemResource(outputGenres))
                .lineAggregator(new DelimitedLineAggregator<>())
                .build();
    }

    @StepScope
    @Bean
    public JpaCursorItemReader<Book> bookReader() throws Exception {

        String jpqlQuery = "from Book";
        JpaCursorItemReader<Book> itemReader = new JpaCursorItemReader<>();
        itemReader.setQueryString(jpqlQuery);
        itemReader.setEntityManagerFactory(entityManager.getEntityManagerFactory());
        itemReader.afterPropertiesSet();
        itemReader.setSaveState(true);
        return itemReader;
    }

    @StepScope
    @Bean
    public FlatFileItemWriter<Book> bookWriter() {
        return new FlatFileItemWriterBuilder<Book>()
                .name("bookItemWriter")
                .resource(new FileSystemResource(outputBooks))
                .lineAggregator(new DelimitedLineAggregator<>())
                .build();
    }

    @StepScope
    @Bean
    public JpaCursorItemReader<Comment> commentReader() throws Exception {

        String jpqlQuery = "from Comment";
        JpaCursorItemReader<Comment> itemReader = new JpaCursorItemReader<>();
        itemReader.setQueryString(jpqlQuery);
        itemReader.setEntityManagerFactory(entityManager.getEntityManagerFactory());
        itemReader.afterPropertiesSet();
        itemReader.setSaveState(true);
        return itemReader;
    }

    @StepScope
    @Bean
    public FlatFileItemWriter<Comment> commentWriter() {
        return new FlatFileItemWriterBuilder<Comment>()
                .name("commentItemWriter")
                .resource(new FileSystemResource(outputComments))
                .lineAggregator(new DelimitedLineAggregator<>())
                .build();
    }


}

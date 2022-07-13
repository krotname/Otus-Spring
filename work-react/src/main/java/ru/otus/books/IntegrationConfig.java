package ru.otus.books;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.PollableChannel;
import ru.otus.books.dao.BookRepository;
import ru.otus.books.dao.Testttt;

@Configuration
public class IntegrationConfig {

    private static final int QUEUE_CAPACITY = 10;
    private static final String COOK_METHOD_NAME = "findAll";

    @Bean
    public PollableChannel bookRequestChannel() {
        return MessageChannels.queue(QUEUE_CAPACITY).get();
    }

    @Bean
    public PublishSubscribeChannel bookResultChannel() {
        return MessageChannels.publishSubscribe().get();
    }


    @Bean
    public IntegrationFlow bookServiceFlow(BookRepository bookRepository) {
        return IntegrationFlows.from(bookRequestChannel())
                .handle(bookRepository, COOK_METHOD_NAME)
                .channel(bookResultChannel())
                .get();
    }
}

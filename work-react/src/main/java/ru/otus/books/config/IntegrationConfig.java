package ru.otus.books.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.PollableChannel;
import ru.otus.books.dao.BookRepository;
import ru.otus.books.integration.ConvertService;

@Configuration
public class IntegrationConfig {

    private static final int QUEUE_CAPACITY = 10;
    private static final String FIND_ALL = "findAll";
    private static final String FIND_BY_ID = "findById";
    private static final String SAVE = "save";
    private static final String TO_DOMAIN_OBJECT = "toDomainObject";
    private static final String TO_DTO = "toDto";
    private static final String LIST_TO_DTO = "listToDto";

    @Bean
    public PollableChannel bookRequestChannel() {
        return MessageChannels.queue(QUEUE_CAPACITY).get();
    }

    @Bean
    public PublishSubscribeChannel bookReplyChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public PollableChannel allBooksRequestChannel() {
        return MessageChannels.queue(QUEUE_CAPACITY).get();
    }

    @Bean
    public PublishSubscribeChannel allBooksReplyChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public PublishSubscribeChannel bookCreateReplyChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public PollableChannel bookCreateRequestChannel() {
        return MessageChannels.queue(QUEUE_CAPACITY).get();
    }

    @Bean
    public IntegrationFlow allBooksServiceFlow(BookRepository bookRepository, ConvertService convertService) {
        return IntegrationFlows.from(allBooksRequestChannel())
                .handle(bookRepository, FIND_ALL)
                .handle(convertService, LIST_TO_DTO)
                .channel(allBooksReplyChannel())
                .get();
    }

    @Bean
    public IntegrationFlow bookServiceFlow(BookRepository bookRepository, ConvertService convertService) {
        return IntegrationFlows.from(bookRequestChannel())
                .handle(bookRepository, FIND_BY_ID)
                .handle(convertService, TO_DTO)
                .channel(bookReplyChannel())
                .get();
    }

    @Bean
    public IntegrationFlow bookSaveServiceFlow(BookRepository bookRepository, ConvertService convertService) {
        return IntegrationFlows.from(bookCreateRequestChannel())
                .handle(convertService, TO_DOMAIN_OBJECT)
                .handle(bookRepository, SAVE)
                .handle(convertService, TO_DTO)
                .channel(bookReplyChannel())
                .get();
    }
}

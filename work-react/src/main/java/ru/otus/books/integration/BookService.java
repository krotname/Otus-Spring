package ru.otus.books.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.books.dto.BookDto;
import ru.otus.books.entity.Book;

import java.util.List;

@MessagingGateway
public interface BookService {

    @Gateway(requestChannel = "bookRequestChannel", replyChannel = "bookResultChannel")
    List<Book> getAllBooks(int i);

    void printBook(String name);

    void createBook(String name, long genre, long author);

    void updateBook(String name, long genre, long author);

    void deleteBook(String name);
}

package ru.otus.books.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.books.dto.BookDto;
import ru.otus.books.entity.Book;

import java.util.List;
import java.util.Optional;

@MessagingGateway
public interface BookService {

    @Gateway(requestChannel = "allBooksRequestChannel", replyChannel = "allBooksReplyChannel")
    List<BookDto> getAllBooks(int i);

    @Gateway(requestChannel = "bookRequestChannel", replyChannel = "bookReplyChannel")
    BookDto getBookById(long id);

    @Gateway(requestChannel = "bookCreateRequestChannel", replyChannel = "bookCreateReplyChannel")
    BookDto saveBook(BookDto dto);

    void deleteBook(String name);
}

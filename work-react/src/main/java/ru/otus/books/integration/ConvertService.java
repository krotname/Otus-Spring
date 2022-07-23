package ru.otus.books.integration;

import org.springframework.stereotype.Service;
import ru.otus.books.dao.BookRepository;
import ru.otus.books.dto.BookDto;
import ru.otus.books.entity.Book;

import java.util.List;
import java.util.Optional;

@Service
public class ConvertService {

    BookRepository bookRepository;
    public Book toDomainObject(BookDto dto) {
        return Book.builder().id(dto.getId()).name(dto.getName()).build();
    }

    public BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getName());
    }

    public List<BookDto> listToDto(List<Book> books) {
        return books.stream().map(book -> new BookDto(book.getId(), book.getName())).toList();    }

    public BookDto toDto(Optional<Book> book) {
        return new BookDto(book.orElseThrow().getId(), book.orElseThrow().getName());
    }
}

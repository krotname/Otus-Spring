package ru.otus.books.dao;

import org.springframework.stereotype.Service;
import ru.otus.books.dto.BookDto;
import ru.otus.books.entity.Book;

import java.util.List;

@Service
public class Testttt {

    public List<BookDto> findAll(int i) {
        System.out.println("++++");
        BookDto book = new BookDto();
        book.setName("aa");
        book.setId(1L);
        return List.of(book);
    }
}

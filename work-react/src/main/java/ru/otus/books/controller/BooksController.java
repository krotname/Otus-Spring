package ru.otus.books.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.books.dao.BookRepository;
import ru.otus.books.dto.BookDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BooksController {

    private final BookRepository dao;

    @GetMapping("/api/persons") // todo хз почему тут persons а в App.js books // не смог разобраться с реактом
    public List<BookDto> getAllBooks() {
        return dao.findAll().stream().map(BookDto::toDto).collect(Collectors.toList());
    }

}

package ru.otus.books.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.books.dao.BookRepository;
import ru.otus.books.dto.BookDto;
import ru.otus.books.entity.Book;
import ru.otus.books.exceptions.NotFoundException;
import ru.otus.books.integration.BookService;
import ru.otus.books.integration.ConvertService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BooksController {

    private final BookRepository repository;
    private final BookService bookService;
    private final ConvertService convertService;

    @GetMapping("/api/books/all")
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks(0);
    }

    @GetMapping("/api/books/{id}")
    public BookDto getBookByIdInPath(@PathVariable("id") long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/api/books")
    public BookDto createNewBook(@RequestBody BookDto dto) {
        return bookService.saveBook(dto);
    }

    @DeleteMapping("/api/books/{id}")
    public String deleteById(@PathVariable("id") long id) {
        repository.deleteById(id);
        return "Ok";
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException ex) {
        return ResponseEntity.badRequest().body("Не найдено");
    }
}

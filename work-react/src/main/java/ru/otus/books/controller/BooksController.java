package ru.otus.books.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.books.dao.BookRepository;
import ru.otus.books.dto.BookDto;
import ru.otus.books.entity.Book;
import ru.otus.books.exceptions.NotFoundException;
import ru.otus.books.integration.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BooksController {

    private final BookRepository repository;
    private final BookService bookService;

    @GetMapping("/api/books/all")
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks(1).stream().map(BookDto::toDto).collect(Collectors.toList());
        //return repository.findAll().stream().map(BookDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/api/books/{id}")
    public BookDto getBookByIdInPath(@PathVariable("id") long id) {
        Book book = repository.findById(id).orElseThrow(NotFoundException::new);
        return BookDto.toDto(book);
    }

    @PostMapping("/api/books")
    public BookDto createNewBook(@RequestBody BookDto dto) {
        Book book = BookDto.toDomainObject(dto);
        Book savedBook = repository.save(book);
        return BookDto.toDto(savedBook);
    }

    @PatchMapping("/api/books/{id}/{name}")
    public BookDto updateNameById(@PathVariable("id") long id, @RequestParam("name") String name) {
        Book book = repository.findById(id).orElseThrow(NotFoundException::new);
        book.setName(name);
        return BookDto.toDto(repository.save(book));
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

package ru.otus.books.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.SneakyThrows;
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
@Slf4j
public class BooksController {

    private final BookRepository repository;
    private final BookService bookService;
    private final ConvertService convertService;

    @GetMapping("/api/books/all")
    @HystrixCommand(fallbackMethod = "defaultListDto")
    @SneakyThrows
    public List<BookDto> getAllBooks() {
        log.info("Запрошенны все Книги");
        return bookService.getAllBooks(0);
    }

    @GetMapping("/api/books/{id}")
    @HystrixCommand(fallbackMethod = "defaultDto")
    public BookDto getBookByIdInPath(@PathVariable("id") long id) {
        log.info("Запрошенна Книга: {}", id);
        return bookService.getBookById(id);
    }

    @PostMapping("/api/books")
    @HystrixCommand
    public BookDto createNewBook(@RequestBody BookDto dto) {
        log.info("Создана Книга: {}", dto);
        return bookService.saveBook(dto);
    }

    @PatchMapping("/api/books/{id}/{name}")
    @HystrixCommand
    public BookDto updateNameById(@PathVariable("id") long id, @RequestParam("name") String name) {
        log.info("Обновлена Книга: {}, новое имя {}", id, name);
        Book book = repository.findById(id).orElseThrow(NotFoundException::new);
        book.setName(name);
        return bookService.saveBook(convertService.toDto(book));
    }

    @DeleteMapping("/api/books/{id}")
    @HystrixCommand(fallbackMethod = "defaultString")
    public String deleteById(@PathVariable("id") long id) {
        log.info("Удаленна Книга: {}", id);
        repository.deleteById(id);
        return "Ok";
    }

    private String defaultString(long id) {
        return "error on load";
    }

    private BookDto defaultDto(long id) {
        return new BookDto(0, "");
    }

    private List<BookDto> defaultListDto() {
        return List.of(new BookDto(0, ""));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException ex) {
        return ResponseEntity.badRequest().body("Не найдено");
    }
}

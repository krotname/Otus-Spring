package ru.otus.books.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.books.dao.BookRepository;
import ru.otus.books.dto.BookDto;
import ru.otus.books.entity.Book;
import ru.otus.books.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BooksController {

    private final BookRepository repository;

    @GetMapping("/api/books/all")
    @HystrixCommand(fallbackMethod = "defaultListDto")
    @SneakyThrows
    public List<BookDto> getAllBooks() {
//        Thread.sleep(50000);
        return repository.findAll().stream().map(BookDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/api/books/{id}")
    @HystrixCommand(fallbackMethod = "defaultDto")
    public BookDto getBookByIdInPath(@PathVariable("id") long id) {
        Book book = repository.findById(id).orElseThrow(NotFoundException::new);
        return BookDto.toDto(book);
    }

    @PostMapping("/api/books")
    @HystrixCommand
    public BookDto createNewBook(@RequestBody BookDto dto) {
        Book book = BookDto.toDomainObject(dto);
        Book savedBook = repository.save(book);
        return BookDto.toDto(savedBook);
    }

    @PatchMapping("/api/books/{id}/{name}")
    @HystrixCommand
    public BookDto updateNameById(@PathVariable("id") long id, @RequestParam("name") String name) {
        Book book = repository.findById(id).orElseThrow(NotFoundException::new);
        book.setName(name);
        return BookDto.toDto(repository.save(book));
    }

    @DeleteMapping("/api/books/{id}")
    @HystrixCommand(fallbackMethod = "defaultString")
    public String deleteById(@PathVariable("id") long id) {
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

package ru.otus.reactivespring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.reactivespring.dao.BookRepository;
import ru.otus.reactivespring.entity.Book;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping("/book")
    public Flux<Book> all() {
        return bookRepository.findAll();
    }

    @GetMapping("/book/{id}")
    public Mono<Book> getById(@PathVariable("id") Long id) {
        return bookRepository.findById(id);
    }

    @DeleteMapping("/book/{id}")
    public Mono<Void> deleteById(@PathVariable("id") Long id) {
        return bookRepository.deleteById(id);
    }

    @PostMapping("/book")
    public Mono<Book> save(@RequestBody Book dto) {
        return bookRepository.save(dto);
    }
}

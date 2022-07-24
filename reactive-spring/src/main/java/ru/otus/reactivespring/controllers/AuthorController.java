package ru.otus.reactivespring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.reactivespring.dao.AuthorRepository;
import ru.otus.reactivespring.entity.Author;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;

    @GetMapping("/author")
    public Flux<Author> all() {
        return authorRepository.findAll();
    }

    @GetMapping("/author/{id}")
    public Mono<Author> getById(@PathVariable("id") Long id) {
        return authorRepository.findById(id);
    }

    @DeleteMapping("/author/{id}")
    public Mono<Void> deleteById(@PathVariable("id") Long id) {
        return authorRepository.deleteById(id);
    }

    @PostMapping("/author")
    public Mono<Author> save(@RequestBody Author dto) {
        return authorRepository.save(dto);
    }
}

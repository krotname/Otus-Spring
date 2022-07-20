package ru.otus.reactivespring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.reactivespring.dao.GenreRepository;
import ru.otus.reactivespring.entity.Genre;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreRepository genreRepository;

    @GetMapping("/genre")
    public Flux<Genre> all() {
        return genreRepository.findAll();
    }

    @GetMapping("/genre/{id}")
    public Mono<Genre> getById(@PathVariable("id") Long id) {
        return genreRepository.findById(id);
    }

    @DeleteMapping("/genre/{id}")
    public Mono<Void> deleteById(@PathVariable("id") Long id) {
        return genreRepository.deleteById(id);
    }

    @PostMapping("/genre")
    public Mono<Genre> save(@RequestBody Genre dto) {
        return genreRepository.save(dto);
    }
}

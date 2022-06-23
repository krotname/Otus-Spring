package ru.otus.reactivespring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class Controller {

    @GetMapping("/flux/one")
    public Mono<String> one() {
        return Mono.just("one")
                .map(String::toUpperCase);
    }

    @GetMapping("/flux/ten")
    public Flux<Integer> list() {
        return Flux.range(1, 10);
    }

}

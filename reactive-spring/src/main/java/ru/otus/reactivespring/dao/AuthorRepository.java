package ru.otus.reactivespring.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.reactivespring.entity.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, Long> {
    Author findByName(String name);
}

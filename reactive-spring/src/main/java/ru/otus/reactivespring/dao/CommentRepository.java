package ru.otus.reactivespring.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.reactivespring.entity.Genre;

public interface CommentRepository extends ReactiveMongoRepository<Genre, Long> {
}

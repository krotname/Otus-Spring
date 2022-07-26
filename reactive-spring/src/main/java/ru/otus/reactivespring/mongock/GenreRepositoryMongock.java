package ru.otus.reactivespring.mongock;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.reactivespring.entity.Genre;

public interface GenreRepositoryMongock extends MongoRepository<Genre, Long> {
    Genre getByName(String name);
}

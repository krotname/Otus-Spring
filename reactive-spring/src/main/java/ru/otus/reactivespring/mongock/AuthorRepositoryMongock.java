package ru.otus.reactivespring.mongock;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.reactivespring.entity.Author;

public interface AuthorRepositoryMongock extends MongoRepository<Author, Long> {
    Author findByName(String name);
}

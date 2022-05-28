package ru.otus.workbooks.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.workbooks.entity.Author;

public interface AuthorRepository extends MongoRepository<Author, Long> {
    Author findByName(String name);
}

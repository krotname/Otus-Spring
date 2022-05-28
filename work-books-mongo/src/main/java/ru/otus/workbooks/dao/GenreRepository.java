package ru.otus.workbooks.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.workbooks.entity.Genre;

public interface GenreRepository extends MongoRepository<Genre, Long> {
    Genre getByName(String name);
}

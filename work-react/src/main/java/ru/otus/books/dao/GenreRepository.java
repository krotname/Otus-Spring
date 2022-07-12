package ru.otus.books.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.books.entity.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {
}

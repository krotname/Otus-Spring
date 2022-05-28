package ru.otus.workbooks.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.workbooks.entity.Genre;

public interface GenreRepository extends CrudRepository<Genre, Long> {
}

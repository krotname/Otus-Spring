package ru.otus.books.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.books.entity.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}

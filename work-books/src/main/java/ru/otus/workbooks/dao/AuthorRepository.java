package ru.otus.workbooks.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.workbooks.entity.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}

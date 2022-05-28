package ru.otus.workbooks.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.workbooks.entity.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
    Book findByName(String name);
}

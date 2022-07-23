package ru.otus.books.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.books.entity.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    Book findByName(String name);
    List<Book> findAll();
}

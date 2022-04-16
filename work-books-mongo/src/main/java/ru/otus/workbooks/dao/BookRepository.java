package ru.otus.workbooks.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.workbooks.entity.Book;

public interface BookRepository extends MongoRepository<Book, Long> {
    Book findByName(String name);
}

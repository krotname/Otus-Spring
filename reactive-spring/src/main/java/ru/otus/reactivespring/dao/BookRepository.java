package ru.otus.reactivespring.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.reactivespring.entity.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, Long> {
    Book findByName(String name);
}

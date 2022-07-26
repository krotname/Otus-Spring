package ru.otus.reactivespring.mongock;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.reactivespring.entity.Book;

public interface BookRepositoryMongock extends MongoRepository<Book, Long> {
}

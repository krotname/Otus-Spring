package ru.otus.workbooks.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.workbooks.entity.Comment;

import java.util.List;

public interface CommentsRepository extends MongoRepository<Comment, Long> {
    List<Comment> findByBookId(String id);

    void removeById(long id);
}

package ru.otus.books.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.books.entity.Comment;

import java.util.List;

public interface CommentsRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByBookId(long id);

    void removeById(long id);
}

package ru.otus.workbooks.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.workbooks.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentsRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByBookId(long id);

    Optional<Comment> findById(Long id);

    void removeById(long id);
}

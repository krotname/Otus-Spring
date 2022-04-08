package ru.otus.workbooks.dao;

import ru.otus.workbooks.entity.Book;
import ru.otus.workbooks.entity.Comment;

import java.util.List;

public interface CommentDao {

    void createComments(Book book, String content);

    List<Comment> readComment(Book book);

    void updateComment(long id, String content);

    void deleteComment(long id);

}

package ru.otus.workbooks.dao;

import ru.otus.workbooks.entity.Book;
import ru.otus.workbooks.entity.Comment;

import java.util.List;

public interface CommentDao {

    Comment createOrUpdateComments(Comment comment);

    List<Comment> getComment(Book book);

    Comment getCommentByID(long id);

    void deleteCommentById(long id);

}

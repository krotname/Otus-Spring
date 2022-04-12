package ru.otus.workbooks.dao;

import ru.otus.workbooks.entity.Comment;

public interface CommentDao {

    Comment createOrUpdateComments(Comment comment);

    Comment getCommentByID(long id);

    void deleteCommentById(long id);

}

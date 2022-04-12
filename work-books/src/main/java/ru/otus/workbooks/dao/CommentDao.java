package ru.otus.workbooks.dao;

import ru.otus.workbooks.entity.Book;

public interface CommentDao {

    void createComments(Book book, String content);

    void updateComment(long id, String content);

    void deleteComment(long id);

}

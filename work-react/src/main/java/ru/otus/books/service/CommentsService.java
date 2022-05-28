package ru.otus.books.service;

public interface CommentsService {

    void createComment(String bookName, String body);

    void readComments(String bookName);

    void updateComment(long id, String content);

    void deleteComment(long id);
}

package ru.otus.workbooks.service;

public interface CommentsService {

    void createComment(String bookName, String body);

    void readComments(String bookName);

    void updateComment(String bookName, int id, String content);

    void deleteComment(String bookName, int id);
}

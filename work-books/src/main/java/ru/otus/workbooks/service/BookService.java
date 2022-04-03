package ru.otus.workbooks.service;

public interface BookService {
    void printAllInfo();

    void printBook(String book);

    void createBook(String name, int genre, int author);

    void updateBook(String name, int genre, int author);

    void deleteBook(String name);
}

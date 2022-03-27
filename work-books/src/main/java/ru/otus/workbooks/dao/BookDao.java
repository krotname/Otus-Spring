package ru.otus.workbooks.dao;

import ru.otus.workbooks.entity.Book;

import java.util.List;

public interface BookDao {
    void createBook(String name, int genre, int author);

    Book readBook(String name);

    void updateBook(String name, int genre, int author);

    void deleteBook(String name);

    List<Book> readAllBooks();
}

package ru.otus.workbooks.dao;

import ru.otus.workbooks.entity.Book;

import java.util.List;

public interface BookDao {
    Book createOrUpdateBook(Book book);

    Book getBook(String name);

    void deleteBook(long id);

    List<Book> readAllBooks();
}

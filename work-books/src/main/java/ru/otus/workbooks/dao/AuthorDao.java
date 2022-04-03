package ru.otus.workbooks.dao;

import ru.otus.workbooks.entity.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> readAllAuthors();
}

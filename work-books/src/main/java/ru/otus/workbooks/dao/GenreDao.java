package ru.otus.workbooks.dao;

import ru.otus.workbooks.entity.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> readAllGenres();

    Genre readGenres(long id);
}

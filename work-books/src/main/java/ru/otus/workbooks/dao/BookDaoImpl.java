package ru.otus.workbooks.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.otus.workbooks.entity.Book;
import ru.otus.workbooks.mappers.BookMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final BookMapper bookMapper;

    @Override
    public void createBook(String name, int genre, int author) {
        MapSqlParameterSource param = new MapSqlParameterSource("name", name);
        param.addValue("genre", genre);
        param.addValue("author", author);
        namedParameterJdbcTemplate.update("INSERT INTO books (name, author, genre) VALUES (:name, :genre, :author)", param);

    }

    @Override
    public Book readBook(String name) {
        SqlParameterSource param = new MapSqlParameterSource("name", name);
        return namedParameterJdbcTemplate.queryForObject("SELECT * FROM BOOKS JOIN GENRES ON BOOKS.genre = GENRES.id JOIN AUTHORS ON BOOKS.author = AUTHORS.id WHERE BOOKS.name = :name ", param, bookMapper);
    }

    @Override
    public void updateBook(String name, int genre, int author) {
        MapSqlParameterSource param = new MapSqlParameterSource("name", name);
        param.addValue("genre", genre);
        param.addValue("author", author);
        namedParameterJdbcTemplate.update("UPDATE books SET author = :author, genre = :genre WHERE books.name= :name", param);
    }

    @Override
    public void deleteBook(String name) {
        MapSqlParameterSource param = new MapSqlParameterSource("name", name);
        namedParameterJdbcTemplate.update("DELETE FROM books WHERE books.name= :name", param);
    }

    @Override
    public List<Book> readAllBooks() {
        return namedParameterJdbcTemplate.query("SELECT * FROM BOOKS JOIN GENRES ON BOOKS.genre = GENRES.id JOIN AUTHORS ON BOOKS.author = AUTHORS.id", bookMapper);
    }
}

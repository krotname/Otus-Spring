package ru.otus.workbooks.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.otus.workbooks.entity.Author;
import ru.otus.workbooks.entity.Book;
import ru.otus.workbooks.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Book.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .genre(Genre.builder()
                        .id(rs.getInt("GENRES.id"))
                        .name(rs.getString("GENRES.name"))
                        .build())
                .author(Author.builder()
                        .id(rs.getInt("AUTHORS.id"))
                        .name(rs.getString("AUTHORS.name"))
                        .build())
                .build();
    }
}

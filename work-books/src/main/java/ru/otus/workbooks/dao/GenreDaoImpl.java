package ru.otus.workbooks.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.workbooks.entity.Genre;
import ru.otus.workbooks.mappers.GenreMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenreDaoImpl implements GenreDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final GenreMapper genreMapper;

    @Override
    public List<Genre> readAllGenres() {
        return namedParameterJdbcTemplate.query("SELECT * FROM GENRES", genreMapper);
    }
}

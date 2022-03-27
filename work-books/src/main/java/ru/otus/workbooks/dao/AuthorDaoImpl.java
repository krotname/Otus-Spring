package ru.otus.workbooks.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.workbooks.entity.Author;
import ru.otus.workbooks.mappers.AuthorMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {

    private final AuthorMapper authorMapper;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Author> readAllAuthors() {
        return namedParameterJdbcTemplate.query("SELECT * FROM AUTHORS", authorMapper);
    }
}

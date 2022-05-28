package ru.otus.workbooks.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.otus.workbooks.entity.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreDaoJpql implements GenreDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Genre> readAllGenres() {
        return em.createQuery("select g from Genre g", Genre.class)
                .getResultList();
    }

    @Override
    public Genre readGenres(long id) {
        return em.find(Genre.class, id);
    }
}

package ru.otus.workbooks.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.workbooks.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorDaoJpql implements AuthorDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Author> readAllAuthors() {
        return em.createQuery("select a from Author a", Author.class)
                .getResultList();
    }

    @Override
    public Author readAuthor(long id) {
        return em.find(Author.class, id);
    }
}

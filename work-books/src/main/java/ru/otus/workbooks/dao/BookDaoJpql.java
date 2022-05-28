package ru.otus.workbooks.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.otus.workbooks.entity.Book;
import ru.otus.workbooks.entity.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookDaoJpql implements BookDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Book createOrUpdateBook(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Book getBook(String name) {
        Query query = em.createQuery("select b from Book as b where b.name =:name", Book.class);
        query.setParameter("name", name);
        return (Book) query.getSingleResult();
    }

    @Override
    public void deleteBook(long id) {
        Book book = em.find(Book.class, id);
        if (book == null) {
            throw new IllegalArgumentException();
        } else {
            em.remove(book);
        }
    }

    @Override
    public List<Book> readAllBooks() {
        return em.createQuery("select b from Book b", Book.class)
                .getResultList();
    }
}

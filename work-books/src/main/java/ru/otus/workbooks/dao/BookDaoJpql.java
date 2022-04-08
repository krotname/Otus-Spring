package ru.otus.workbooks.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.workbooks.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookDaoJpql implements BookDao {

    @PersistenceContext
    private final EntityManager em;

    public Book createBook(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    public Book readBook(String name) {
        Query query = em.createQuery("select b from Book as b where b.name =:name", Book.class);
        query.setParameter("name", name);
        return (Book) query.getSingleResult();
    }

    public void updateBook(String name, int genre, int author) {
        Query query = em.createQuery("update Book b " +
                "set b.name = :name " +
                ", b.genre = :genre " +
                ", b.author = :author " +
                "where b.id = :id");
        query.setParameter("name", name);
        query.setParameter("genre", genre);
        query.setParameter("author", author);

        query.executeUpdate();
    }

    public void deleteBook(String name) {
        Query query = em.createQuery("delete " +
                "from Book b " +
                "where b.name = :name");
        query.setParameter("name", name);
        query.executeUpdate();
    }

    public List<Book> readAllBooks() {
        return em.createQuery("select b from Book b", Book.class)
                .getResultList();
    }
}

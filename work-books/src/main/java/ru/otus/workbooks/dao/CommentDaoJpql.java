package ru.otus.workbooks.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.workbooks.entity.Book;
import ru.otus.workbooks.entity.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentDaoJpql implements CommentDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Comment createOrUpdateComments(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
            return comment;
        }
        return em.merge(comment);
    }

    @Override
    public List<Comment> getComment(Book book) {
        Query query = em.createQuery("select c from Comment as c where c.book_id =:book_id", Comment.class);
        query.setParameter("book_id", book.getId());
        List<Comment> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public Comment getCommentByID(long id) {
        return em.find(Comment.class, id);
    }

    @Override
    public void deleteCommentById(long id) {
        Comment comment = em.find(Comment.class, id);
        if (comment == null) {
            throw new IllegalArgumentException();
        } else {
            em.remove(comment);
        }
    }
}

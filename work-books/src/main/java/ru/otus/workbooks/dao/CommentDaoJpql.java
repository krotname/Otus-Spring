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
    public void createComments(Book book, String content) {
        Comment comment = Comment.builder()
                .content(content)
                .book_id(book.getId())
                .build();

        em.merge(comment);
    }

    @Override
    public List<Comment> readComment(Book book) {
        Query query = em.createQuery("select c from Comment as c where c.book_id =:book_id", Comment.class);
        query.setParameter("book_id", book.getId());
        List<Comment> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public void updateComment(long id, String content) {
        Query query = em.createQuery("update Comment b " +
                "set b.content = :content " +
                "where b.id = :id");
        query.setParameter("content", content);
        query.setParameter("id", id);

        query.executeUpdate();
    }

    @Override
    public void deleteComment(long id) {
        Query query = em.createQuery("delete " +
                "from Comment b " +
                "where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}

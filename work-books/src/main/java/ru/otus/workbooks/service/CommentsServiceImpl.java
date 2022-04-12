package ru.otus.workbooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.workbooks.dao.BookDao;
import ru.otus.workbooks.dao.CommentDao;
import ru.otus.workbooks.entity.Book;
import ru.otus.workbooks.entity.Comment;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final BookDao bookDao;
    private final CommentDao commentDao;
    private final IOService ioService;

    @Override
    @Transactional
    public void createComment(String bookName, String content) {
        Book book = bookDao.getBook(bookName);

        Comment comment = Comment.builder()
                .content(content)
                .book(book)
                .build();

        commentDao.createOrUpdateComments(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public void readComments(String bookName) {
        Book book = bookDao.getBook(bookName);
        ioService.print(book.getComments().toString());
    }

    @Override
    @Transactional
    public void updateComment(long id, String content) {
        Comment commentByID = commentDao.getCommentByID(id);
        commentByID.setContent(content);
        commentDao.createOrUpdateComments(commentByID);
    }

    @Override
    @Transactional
    public void deleteComment(long id) {
        commentDao.deleteCommentById(id);
    }
}

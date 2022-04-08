package ru.otus.workbooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.workbooks.dao.BookDao;
import ru.otus.workbooks.dao.CommentDao;
import ru.otus.workbooks.entity.Book;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final BookDao bookDao;
    private final CommentDao commentDao;
    private final IOService ioService;

    @Override
    @Transactional
    public void createComment(String bookName, String content) {
        Book book = bookDao.readBook(bookName);
        commentDao.createComments(book, content);
    }

    @Override
    @Transactional(readOnly = true)
    public void readComments(String bookName) {
        Book book = bookDao.readBook(bookName);
        ioService.print(commentDao.readComment(book).toString());
    }


    @Override
    @Transactional
    public void updateComment(long id, String content) {
        commentDao.updateComment(id, content);
    }

    @Override
    @Transactional
    public void deleteComment(long id) {
        commentDao.deleteComment(id);
    }
}

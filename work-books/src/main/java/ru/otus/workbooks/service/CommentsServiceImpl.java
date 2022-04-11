package ru.otus.workbooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.workbooks.dao.BookRepository;
import ru.otus.workbooks.dao.CommentsRepository;
import ru.otus.workbooks.entity.Book;
import ru.otus.workbooks.entity.Comment;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final BookRepository bookRepository;
    private final CommentsRepository commentsRepository;
    private final IOService ioService;

    @Override
    @Transactional
    public void createComment(String bookName, String content) {
        Book book = bookRepository.findByName(bookName);

        Comment comment = Comment.builder()
                .bookId(book.getId())
                .content(content)
                .build();

        commentsRepository.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public void readComments(String bookName) {
        Book book = bookRepository.findByName(bookName);
        ioService.print(commentsRepository.findByBookId(book.getId()).toString());
    }


    @Override
    @Transactional
    public void updateComment(long id, String content) {
        Comment comment = commentsRepository.findById(id).orElseThrow();
        comment.setContent(content);
        commentsRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteComment(long id) {
        commentsRepository.removeById(id);
    }
}

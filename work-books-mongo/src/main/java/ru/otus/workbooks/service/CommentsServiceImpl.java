package ru.otus.workbooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.workbooks.dao.BookRepository;
import ru.otus.workbooks.dao.CommentsRepository;
import ru.otus.workbooks.entity.Book;
import ru.otus.workbooks.entity.Comment;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final BookRepository bookRepository;
    private final CommentsRepository commentsRepository;
    private final IOService ioService;

    @Override
    public void createComment(String bookName, String content) {
        Book book = bookRepository.findByName(bookName);

        Comment comment = Comment.builder()
                .id(UUID.randomUUID().toString())
                .book(book)
                .content(content)
                .build();

        commentsRepository.save(comment);
    }

    @Override
    public void readComments(String bookName) {
        Book book = bookRepository.findByName(bookName);
        ioService.print(commentsRepository.findByBookId(book.getId()).toString());
    }

    @Override
    public void updateComment(long id, String content) {
        Comment comment = commentsRepository.findById(id).orElseThrow();
        comment.setContent(content);
        commentsRepository.save(comment);
    }

    @Override
    public void deleteComment(long id) {
        commentsRepository.removeById(id);
    }
}

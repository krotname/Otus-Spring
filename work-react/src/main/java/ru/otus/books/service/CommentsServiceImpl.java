package ru.otus.books.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.books.dao.BookRepository;
import ru.otus.books.dao.CommentsRepository;
import ru.otus.books.entity.Book;
import ru.otus.books.entity.Comment;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final BookRepository bookRepository;
    private final CommentsRepository commentsRepository;

    @Override
    public void createComment(String bookName, String content) {
        Book book = bookRepository.findByName(bookName);

        Comment comment = Comment.builder()
                .book(book)
                .content(content)
                .build();

        commentsRepository.save(comment);
    }

    @Override
    public void readComments(String bookName) {
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

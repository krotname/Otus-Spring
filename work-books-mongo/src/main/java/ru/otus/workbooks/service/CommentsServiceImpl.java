package ru.otus.workbooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.workbooks.dao.BookRepository;
import ru.otus.workbooks.entity.Book;
import ru.otus.workbooks.entity.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final BookRepository bookRepository;
    private final IOService ioService;

    @Override
    public void createComment(String bookName, String content) {
        Book book = bookRepository.findByName(bookName);

        Comment comment = Comment.builder()
                .id(UUID.randomUUID().toString())
                .content(content)
                .build();

        List<Comment> comments = book.getComments();;

        if (comments == null) {
            comments = new ArrayList<>();
        }

        comments.add(comment);
        book.setComments(comments);

        bookRepository.save(book);
    }

    @Override
    public void readComments(String bookName) {
        Book book = bookRepository.findByName(bookName);
        ioService.print(book.getComments().toString());
    }

    @Override
    public void updateComment(String bookName, int id, String content) {
        Book book = bookRepository.findByName(bookName);
        book.getComments().get(id).setContent(content);
        bookRepository.save(book);
    }

    @Override
    public void deleteComment(String bookName, int id) {
        Book book = bookRepository.findByName(bookName);
        book.getComments().remove(id);
        bookRepository.save(book);
    }
}

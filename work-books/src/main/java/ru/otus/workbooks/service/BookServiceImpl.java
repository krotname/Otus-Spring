package ru.otus.workbooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.workbooks.dao.AuthorDao;
import ru.otus.workbooks.dao.BookDao;
import ru.otus.workbooks.dao.GenreDao;
import ru.otus.workbooks.entity.Book;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final AuthorDao authorDao;
    private final IOService ioService;

    @Override
    @Transactional(readOnly = true)
    public void printAllInfo() {
        ioService.print("---- Доступные жанры в базе: ");
        ioService.print(genreDao.readAllGenres().toString());
        ioService.print("");
        ioService.print("---- Доступные авторы в базе: ");
        ioService.print(authorDao.readAllAuthors().toString());
        ioService.print("");
        ioService.print("---- Доступные книги в базе: ");
        ioService.print(bookDao.readAllBooks().toString());
        ioService.print("");
    }

    @Override
    @Transactional(readOnly = true)
    public void printBook(String book) {
        ioService.print(bookDao.readBook(book).toString());
    }

    @Override
    @Transactional
    public void createBook(String name, int genre, int author) {
        Book book = Book.builder()
                .genre(genreDao.readGenres(genre))
                .author(authorDao.readAuthor(author))
                .name(name)
                .build();

        bookDao.createBook(book);
    }

    @Override
    @Transactional
    public void updateBook(String name, int genre, int author) {
        bookDao.updateBook(name, genre, author);
    }

    @Override
    @Transactional
    public void deleteBook(String name) {
        bookDao.deleteBook(name);
    }

}

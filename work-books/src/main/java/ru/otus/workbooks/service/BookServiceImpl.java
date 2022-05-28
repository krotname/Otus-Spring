package ru.otus.workbooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.workbooks.dao.AuthorDao;
import ru.otus.workbooks.dao.BookDao;
import ru.otus.workbooks.dao.GenreDao;
import ru.otus.workbooks.entity.Author;
import ru.otus.workbooks.entity.Book;
import ru.otus.workbooks.entity.Genre;


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
    public void printBook(String book) {
        ioService.print(bookDao.getBook(book).toString());
    }

    @Override
    @Transactional
    public void createBook(String name, long genre, long author) {
        Book book = Book.builder()
                .genre(genreDao.readGenres(genre))
                .author(authorDao.readAuthor(author))
                .name(name)
                .build();

        bookDao.createOrUpdateBook(book);
    }

    @Override
    @Transactional
    public void updateBook(String name, long genreId, long authorId) {
        Book book = bookDao.getBook(name);

        if (authorId > 0) {
            Author author = authorDao.readAuthor(authorId);
            book.setAuthor(author);
        }

        if (genreId > 0){
            Genre genre = genreDao.readGenres(genreId);
            book.setGenre(genre);
        }

        bookDao.createOrUpdateBook(book);
    }

    @Override
    @Transactional
    public void deleteBook(String name) {
        Book book = bookDao.getBook(name);
        bookDao.deleteBook(book.getId());
    }

}

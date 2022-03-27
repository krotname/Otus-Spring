package ru.otus.workbooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.workbooks.dao.AuthorDao;
import ru.otus.workbooks.dao.BookDao;
import ru.otus.workbooks.dao.GenreDao;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final AuthorDao authorDao;

    @Override
    public void printAllInfo() {
        System.out.println("---- Доступные жанры в базе: ");
        System.out.println(genreDao.readAllGenres());
        System.out.println();
        System.out.println("---- Доступные авторы в базе: ");
        System.out.println(authorDao.readAllAuthors());
        System.out.println();
        System.out.println("---- Доступные книги в базе: ");
        System.out.println(bookDao.readAllBooks());
        System.out.println();
    }

    @Override
    public void printBook(String book) {
        System.out.println(bookDao.readBook(book));
    }

    @Override
    public void createBook(String name, int genre, int author) {
        bookDao.createBook(name, genre, author);
    }

    @Override
    public void updateBook(String name, int genre, int author) {
        bookDao.updateBook(name, genre, author);
    }

    @Override
    public void deleteBook(String name) {
        bookDao.deleteBook(name);
    }

}

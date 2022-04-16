package ru.otus.workbooks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.workbooks.dao.AuthorRepository;
import ru.otus.workbooks.dao.BookRepository;
import ru.otus.workbooks.dao.GenreRepository;
import ru.otus.workbooks.entity.Book;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final IOService ioService;

    @Override
    public void printAllInfo() {
        ioService.print("---- Доступные жанры в базе: ");
        ioService.print(genreRepository.findAll().toString());
        ioService.print("");
        ioService.print("---- Доступные авторы в базе: ");
        ioService.print(authorRepository.findAll().toString());
        ioService.print("");
        ioService.print("---- Доступные книги в базе: ");
        ioService.print(bookRepository.findAll().toString());
        ioService.print("");
    }

    @Override
    public void printBook(String name) {
        ioService.print(bookRepository.findByName(name).toString());
    }

    @Override
    public void createBook(String name, long genre, long author) {
        Book book = Book.builder()
                .id(UUID.randomUUID().toString())
                .genre(genreRepository.findById(genre).orElseThrow())
                .author(authorRepository.findById(author).orElseThrow())
                .name(name)
                .build();

        bookRepository.save(book);
    }

    @Override
    public void updateBook(String name, long genre, long author) {
        Book book = bookRepository.findByName(name);
        book.setAuthor(authorRepository.findById(author).orElseThrow());
        book.setGenre(genreRepository.findById(genre).orElseThrow());
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(String name) {
        Book book = bookRepository.findByName(name);
        bookRepository.delete(book);
    }

}

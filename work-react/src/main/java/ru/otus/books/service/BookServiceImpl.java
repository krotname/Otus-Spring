package ru.otus.books.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.books.dao.AuthorRepository;
import ru.otus.books.dao.BookRepository;
import ru.otus.books.dao.GenreRepository;
import ru.otus.books.entity.Book;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Override
    public void printAllInfo() {
    }

    @Override
    public void printBook(String name) {
    }

    @Override
    public void createBook(String name, long genre, long author) {
        Book book = Book.builder()
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

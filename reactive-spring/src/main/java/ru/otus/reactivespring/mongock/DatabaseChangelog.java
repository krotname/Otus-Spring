package ru.otus.reactivespring.mongock;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.reactivespring.dao.AuthorRepository;
import ru.otus.reactivespring.dao.BookRepository;
import ru.otus.reactivespring.dao.GenreRepository;
import ru.otus.reactivespring.entity.Author;
import ru.otus.reactivespring.entity.Book;
import ru.otus.reactivespring.entity.Genre;

import java.util.UUID;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "Andrey", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertGenres", author = "Andrey")
    public void insertGenres(GenreRepositoryMongock repository) {
        repository.save(Genre.builder().id(UUID.randomUUID().toString()).name("Фантастика").build());
        repository.save(Genre.builder().id(UUID.randomUUID().toString()).name("Детектив").build());
        repository.save(Genre.builder().id(UUID.randomUUID().toString()).name("Любовный роман").build());
        repository.save(Genre.builder().id(UUID.randomUUID().toString()).name("Исторический роман").build());
        repository.save(Genre.builder().id(UUID.randomUUID().toString()).name("Компьютерные науки").build());

    }

    @ChangeSet(order = "003", id = "insertAuthors", author = "Andrey")
    public void insertAuthors(AuthorRepositoryMongock repository) {
        repository.save(Author.builder().id(UUID.randomUUID().toString()).name("Бернар Миньер").build());
        repository.save(Author.builder().id(UUID.randomUUID().toString()).name("Лорет Энн Уайт").build());
        repository.save(Author.builder().id(UUID.randomUUID().toString()).name("Майк Омер").build());
        repository.save(Author.builder().id(UUID.randomUUID().toString()).name("Борис Акунин").build());
        repository.save(Author.builder().id(UUID.randomUUID().toString()).name("Мария Адольфссон").build());
        repository.save(Author.builder().id(UUID.randomUUID().toString()).name("Бхаргава Адитья").build());
    }

    @ChangeSet(order = "004", id = "insertBooks", author = "Andrey")
    public void insertBooks(BookRepositoryMongock bookRepository, AuthorRepositoryMongock authorRepository, GenreRepositoryMongock genreRepository) {
        bookRepository.save(Book.builder()
                .id(UUID.randomUUID().toString())
                .name("Долина")
                .author(authorRepository.findByName("Бернар Миньер"))
                .genre(genreRepository.getByName("Детектив"))
                .build());

        bookRepository.save(Book.builder()
                .id(UUID.randomUUID().toString())
                .name("Мост Дьявола")
                .author(authorRepository.findByName("Лорет Энн Уайт"))
                .genre(genreRepository.getByName("Детектив"))
                .build());

        bookRepository.save(Book.builder()
                .id(UUID.randomUUID().toString())
                .name("Глазами жертвы")
                .author(authorRepository.findByName("Майк Омер"))
                .genre(genreRepository.getByName("Детектив"))
                .build());

        bookRepository.save(Book.builder()
                .id(UUID.randomUUID().toString())
                .name("Просто")
                .author(authorRepository.findByName("Борис Акунин"))
                .genre(genreRepository.getByName("Детектив"))
                .build());


        bookRepository.save(Book.builder()
                .id(UUID.randomUUID().toString())
                .name("Штормовое предупреждение")
                .author(authorRepository.findByName("Мария Адольфссон"))
                .genre(genreRepository.getByName("Детектив"))
                .build());

        bookRepository.save(Book.builder()
                .id(UUID.randomUUID().toString())
                .name("Грокаем Алгоритмы")
                .author(authorRepository.findByName("Бхаргава Адитья"))
                .genre(genreRepository.getByName("Компьютерные науки"))
                .build());
    }
}

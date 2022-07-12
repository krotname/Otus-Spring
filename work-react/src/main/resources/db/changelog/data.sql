--liquibase formatted sql
--changeset InsertIngenres_sql:2

INSERT INTO genres (name)
VALUES ('Фантастика ');

INSERT INTO genres (name)
VALUES ('Детектив');

INSERT INTO genres (name)
VALUES ('Любовный роман');

INSERT INTO genres (name)
VALUES ('Исторический роман');

INSERT INTO genres (name)
VALUES ('Компьютерные науки');

--changeset InsertInauthors_sql:3

INSERT INTO authors (name)
VALUES ('Бернар Миньер');

INSERT INTO authors (name)
VALUES ('Лорет Энн Уайт');

INSERT INTO authors (name)
VALUES ('Майк Омер');

INSERT INTO authors (name)
VALUES ('Борис Акунин');

INSERT INTO authors (name)
VALUES ('Мария Адольфссон');

INSERT INTO authors (name)
VALUES ('Бхаргава Адитья');

--changeset InsertInBooks_sql:4

INSERT INTO books (name, author_id, genre_id)
VALUES ('Долина', 1, 2);

INSERT INTO books (name, author_id, genre_id)
VALUES ('Мост Дьявола', 2, 2);

INSERT INTO books (name, author_id, genre_id)
VALUES ('Глазами жертвы', 3, 2);

INSERT INTO books (name, author_id, genre_id)
VALUES ('Просто', 4, 2);

INSERT INTO books (name, author_id, genre_id)
VALUES ('Штормовое предупреждение', 5, 2);

INSERT INTO books (name, author_id, genre_id)
VALUES ('Грокаем Алгоритмы', 6, 5);

INSERT INTO comments (book_id, content)
VALUES (6, 'Доступно про рекурсию!');

INSERT INTO comments (book_id, content)
VALUES (1, 'Захватывающе!');

INSERT INTO comments (book_id, content)
VALUES (1, 'Очень Захватывающе!');

INSERT INTO users (login, password, role)
VALUES ('user', 'user', 'USER');

INSERT INTO users (login, password, role)
VALUES ('admin', '1', 'ADMIN');

INSERT INTO users (login, password, role)
VALUES ('manager', '2', 'MANAGER');
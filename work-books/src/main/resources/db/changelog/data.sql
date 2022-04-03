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

INSERT INTO books (name, author, genre)
VALUES ('Долина', 1, 2);

INSERT INTO books (name, author, genre)
VALUES ('Мост Дьявола', 2, 2);

INSERT INTO books (name, author, genre)
VALUES ('Глазами жертвы', 3, 2);

INSERT INTO books (name, author, genre)
VALUES ('Просто Маса', 4, 2);

INSERT INTO books (name, author, genre)
VALUES ('Штормовое предупреждение', 5, 2);

INSERT INTO books (name, author, genre)
VALUES ('Грокаем Алгоритмы', 6, 5);

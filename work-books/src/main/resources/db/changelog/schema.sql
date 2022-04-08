--liquibase formatted sql
--changeset CreateTables_sql:1

CREATE TABLE books
(
    id BIGINT auto_increment primary key,
    name VARCHAR NOT NULL,
    author_id BIGINT,
    genre_id BIGINT
);

CREATE TABLE authors
(
    id BIGINT auto_increment primary key,
    name VARCHAR NOT NULL
);

CREATE TABLE genres
(
    id BIGINT auto_increment primary key,
    name VARCHAR NOT NULL
);

CREATE TABLE comments
(
    id BIGINT auto_increment primary key,
    content VARCHAR NOT NULL,
    book_id BIGINT NOT NULL
);

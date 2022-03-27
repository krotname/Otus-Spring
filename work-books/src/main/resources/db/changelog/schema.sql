--liquibase formatted sql
--changeset CreateTables_sql:1

CREATE TABLE books
(
    id INT auto_increment primary key,
    name VARCHAR NOT NULL,
    author INT,
    genre INT
);

CREATE TABLE authors
(
    id INT auto_increment primary key,
    name VARCHAR NOT NULL
);

CREATE TABLE genres
(
    id INT auto_increment primary key,
    name VARCHAR NOT NULL
);

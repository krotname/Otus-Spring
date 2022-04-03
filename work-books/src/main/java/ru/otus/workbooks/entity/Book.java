package ru.otus.workbooks.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class Book {
    private final long id;
    private final String name;
    private final Author author;
    private final Genre genre;
}

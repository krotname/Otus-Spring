package ru.otus.workbooks.entity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class Author {
    private final long id;
    private final String name;
}

package ru.otus.workbooks.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Document(collection = "books")
public class Book {
    @Id
    @ToString.Include
    private String id;

    @ToString.Include
    private String name;

    @ToString.Include
    private Author author;

    @ToString.Include
    private Genre genre;

    @ToString.Include
    private List<Comment> comments;
}

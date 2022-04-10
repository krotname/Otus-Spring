package ru.otus.workbooks.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @ToString.Include
    private long id;

    @Column(name = "name", nullable = false)
    @ToString.Include
    private String name;

    @JoinColumn(name = "author_id")
    @ManyToOne
    @ToString.Include
    private Author author;

    @JoinColumn(name = "genre_id")
    @ManyToOne
    @ToString.Include
    private Genre genre;

    @OneToMany
    @JoinColumn(name = "book_id")
    @Fetch(FetchMode.SUBSELECT)
    @ToString.Include

    private List<Comment> comments;
}

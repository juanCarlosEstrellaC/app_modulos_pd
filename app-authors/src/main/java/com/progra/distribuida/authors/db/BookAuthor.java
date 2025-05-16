package com.progra.distribuida.authors.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books_authors")
@Getter
@Setter
public class BookAuthor {

    @EmbeddedId
    private BookAuthorId id;

    @ManyToOne
    @MapsId("authorId")
    @JoinColumn(name = "authors_id", nullable = false)
    private Author author;
}


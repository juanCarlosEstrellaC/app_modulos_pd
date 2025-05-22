package com.progra.distribuida.authors.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "books_authors")
// Esta clase modela la tabla intermedia books_authors o "publicaciones"
public class BookAuthor {

    // Usa Embedded Id para indicar que la PK es compuesta
    @EmbeddedId
    private BookAuthorId id;

    /*
    Aqui digo: en la columna authors_id de la PK compuesta, debe ir la PK de la tabla authors, naturalmente.
    Ie, el campo author se corresponde con la parte authorId del objeto BookAuthorId (la clave compuesta).
    */
    @ManyToOne
    @MapsId("authorId")
    @JoinColumn(name = "authors_id", nullable = false)
    private Author author;
}


package com.progra.distribuida.authors.repo;

import com.progra.distribuida.authors.db.Author;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class AuthorRepository implements PanacheRepositoryBase<Author, Integer> {

    public List<Author> findByBook(String isbn) {
        // Query JPQL para buscar los autores por el ISBN del libro. ba.id.bookIsbn es queda en la tabla BookAuthor, "id"
        // es la clave compuesta y bookIsbn es el campo que contiene el ISBN del libro.
        return this.find("SELECT ba.author FROM BookAuthor ba WHERE ba.id.bookIsbn = ?1", isbn).list();
    }

    public Optional<Author> update(Integer id, Author author) {
        var obj = this.findByIdOptional(id);
        if (obj.isEmpty()) {
            return Optional.empty();
        }

        var authorObj = obj.get();
        authorObj.setName(author.getName());
        authorObj.setVersion(author.getVersion());
        return Optional.of(authorObj);
    }
}

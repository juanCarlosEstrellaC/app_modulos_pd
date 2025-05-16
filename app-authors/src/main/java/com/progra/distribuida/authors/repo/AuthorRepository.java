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
        return this.find("select o.author from BookAuthor o where o.id.bookIsbn = ?1", isbn).list();
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

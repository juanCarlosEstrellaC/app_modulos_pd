package com.programacion.distribuida.repo;


import com.programacion.distribuida.db.Post;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PostRepository {

    @Inject
    EntityManager em;

    public List<Post> findAll() {
      return  em.createQuery("select p from Post p", Post.class)
                .getResultList();
    }

    public Optional<Post> findId(Integer id) {
       var ret= em.find(Post.class, id);
       return Optional.ofNullable(ret);
    }
}

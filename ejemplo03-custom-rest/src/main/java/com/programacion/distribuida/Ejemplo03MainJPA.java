package com.programacion.distribuida;

import com.programacion.distribuida.db.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

public class Ejemplo03MainJPA {
    public static void main(String[] args) throws Exception {

        EntityManagerFactory emf= Persistence.createEntityManagerFactory("posts-unit");
        EntityManager em = emf.createEntityManager();

        var post1 = em.find(Post.class, 1);
        System.out.println(post1);
    }
}

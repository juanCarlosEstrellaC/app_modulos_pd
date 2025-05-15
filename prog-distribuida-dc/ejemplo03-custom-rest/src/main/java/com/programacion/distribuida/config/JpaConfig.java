package com.programacion.distribuida.config;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.Produces;

@ApplicationScoped()
public class JpaConfig {

    private EntityManagerFactory emf;

    @PostConstruct
    public void init() {
        emf = Persistence.createEntityManagerFactory("posts-unit");
    }
    @Produces
    public EntityManager entityManager(){
      return emf.createEntityManager();
    }
}

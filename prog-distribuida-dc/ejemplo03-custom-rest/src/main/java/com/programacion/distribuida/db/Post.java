package com.programacion.distribuida.db;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name="posts")



public class Post {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 64)
    private String titulo;

    @Column(length = 1024)
    private String body;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

}

package com.programacion.distribuida;

import jakarta.ws.rs.SeBootstrap;

import java.net.URI;

public class Ejemplo03Main {

    public static void main(String[] args) throws Exception {

        var config = SeBootstrap.Configuration.builder().
                port(8080).
                build();

        SeBootstrap.start(RestApplication.class, config)
                .thenApply(instance ->{
                    URI uri =instance.configuration().baseUri();
                    System.out.printf("Instancia %s runnit ar %s", instance,uri);
                    return instance;
                } ).exceptionally(ex->{
                    ex.printStackTrace();
                    return null;
                });

        Thread.currentThread().join();
    }
}

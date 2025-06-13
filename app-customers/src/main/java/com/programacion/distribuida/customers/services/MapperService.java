package com.programacion.distribuida.customers.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class MapperService {

    @Produces
    @ApplicationScoped
    public ModelMapper mapper() {
        return new ModelMapper();
    }
}

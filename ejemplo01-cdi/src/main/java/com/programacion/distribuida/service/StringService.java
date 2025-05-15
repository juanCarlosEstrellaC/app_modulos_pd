package com.programacion.distribuida.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;

public interface StringService {
    String convert(String txt);
}

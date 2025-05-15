package com.programacion.distribuida.service;



import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StringServiceImpl implements StringService {

 ;

//     @PostConstruct
//     public void init() {
//         log= CDI.current().
//                 select(LogService.class).
//                 get();
//     }
    @Override
    public String convert(String txt) {

        return txt.toUpperCase();
    }
}

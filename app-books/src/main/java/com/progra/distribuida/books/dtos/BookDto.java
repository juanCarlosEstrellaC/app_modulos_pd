package com.progra.distribuida.books.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class BookDto {
    private String isbn;
    private String title;
    private BigDecimal price;
    private Integer inventarySold;
    private Integer inventarySupplied;

    private List<String> authors;

}


package com.programacion.distribuida.customers.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BookDto {
    private String isbn;
    private String title;
    private BigDecimal price;

    private Integer inventorySold;
    private Integer inventorySupplied;

    private List<String> authors;
}

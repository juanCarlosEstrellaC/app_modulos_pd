package com.programacion.distribuida.customers.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class LineItemDto {
    private Integer id;
    private Integer quantity;

    private String isbn;
    private String title;
    private BigDecimal price;

    private List<String> authors;
}

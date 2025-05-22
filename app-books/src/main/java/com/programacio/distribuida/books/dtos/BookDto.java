package com.programacio.distribuida.books.dtos;

import com.programacio.distribuida.books.db.Inventory;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@ToString
@Getter
@Setter
public class BookDto {

    private String isbn;
    private String title;
    private BigDecimal price;

    private Integer inventaySold;
    private Integer inventaySupplied;

    private List<String> authors;
}

package com.programacion.distribuida.customers.db;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "line_items")
@Data
@ToString(exclude = {"purchaseOrder"})
public class LineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private PurchaseOrder purchaseOrder;

    private Integer quantity;

    @Column(length = 64)
    private String isbn;
}

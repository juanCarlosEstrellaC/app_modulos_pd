package com.programacion.distribuida.customers.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString(exclude = {"customer", "lineItems"})
@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {
    public enum Status {PENDING, DELIVERED};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Integer total;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.PENDING;

    @Column(name = "placed_on")
    private LocalDateTime placedOn;

    @Column(name = "delivered_on")
    private LocalDateTime deliveredOn;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineItem> lineItems;
}

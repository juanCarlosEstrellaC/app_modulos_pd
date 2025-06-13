package com.programacion.distribuida.customers.repo;

import com.programacion.distribuida.customers.db.PurchaseOrder;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
@Transactional
public class PurchaseOrderRepository implements PanacheRepositoryBase<PurchaseOrder, Integer> {
    public List<PurchaseOrder> findByCustomerId(Integer customerId) {
        return find("customer.id", customerId)
                .list();
    }
}

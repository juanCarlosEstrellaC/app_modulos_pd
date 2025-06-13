package com.programacion.distribuida.customers.repo;

import com.programacion.distribuida.customers.db.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class CustomerRepository implements PanacheRepositoryBase<Customer, Integer> {

}

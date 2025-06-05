package customers.repo;

import customers.db.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
@Transactional
public class CustomersRepository implements PanacheRepositoryBase<Customer, Integer> {


    public Optional<Customer> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }

    public Optional<Customer> updateName(Integer id, String newName) {
        var customer = findByIdOptional(id);
        if (customer.isEmpty()) {
            return Optional.empty();
        }
        customer.get().setName(newName);
        return customer;
    }

    public boolean deleteById(Integer id) {
        return delete("id", id) > 0;
    }

}
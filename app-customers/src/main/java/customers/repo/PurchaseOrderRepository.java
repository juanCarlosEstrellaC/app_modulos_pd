package customers.repo;

import customers.db.Customer;
import customers.db.PurchaseOrder;
import customers.dtos.PurchaseDto;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
@Transactional
public class PurchaseOrderRepository implements PanacheRepositoryBase<PurchaseDto, Integer> {


}
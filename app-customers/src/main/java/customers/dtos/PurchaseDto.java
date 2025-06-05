package customers.dtos;

import customers.db.Customer;
import customers.db.LineItem;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime delivered_on;

    private LocalDateTime placed_on;

    private Integer status;

    private Integer total;

    //private Integer customerId;


}

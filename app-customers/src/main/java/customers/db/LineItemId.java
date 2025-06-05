package customers.db;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class LineItemId implements Serializable {

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "idx")
    private Integer idx;
}

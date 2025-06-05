package customers.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class CustomerDto {

    private Integer id;

    private String name;

    private String email;

    private Integer version;
}

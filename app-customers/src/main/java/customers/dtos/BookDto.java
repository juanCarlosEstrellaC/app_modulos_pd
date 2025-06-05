package customers.dtos;

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

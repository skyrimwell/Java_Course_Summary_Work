package app.Application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrdersSearchDto {
    private String orderDate;
    private Long brickPrice;
}

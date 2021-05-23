package app.Application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderSearchDto {
    private String orderDate;
    private Long price;
}

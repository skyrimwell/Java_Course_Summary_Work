package app.Application.dto;
import app.Application.Classes.Brick;
import app.Application.Classes.Orders;
import app.Application.Classes.Orderlist;
import app.Application.Classes.OrderlistMultiid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderlistAddDto {
    private OrderlistMultiid orderlistMultiid;

    private Long count;

    private Orders orders;

    private Brick brick;

    public Orderlist toEntity(){
        return Orderlist.builder()
                .orderlistMultiid(orderlistMultiid)
                .count(count)
                .orders(orders)
                .brick(brick)
                .build();
    }
}

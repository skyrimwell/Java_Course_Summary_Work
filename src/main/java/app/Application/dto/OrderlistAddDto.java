package app.Application.dto;
import app.Application.Classes.Brick;
import app.Application.Classes.Order;
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

    private Long  count;

    private Order order;

    private Brick brick;

    public Orderlist toEntity(){
        return Orderlist.builder()
                .orderlistMultiid(orderlistMultiid)
                .count(count)
                .order(order)
                .brick(brick)
                .build();
    }
}

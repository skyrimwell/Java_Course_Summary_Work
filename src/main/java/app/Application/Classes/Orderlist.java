package app.Application.Classes;

import app.Application.Classes.Brick;
import app.Application.Classes.Order;
import app.Application.Classes.OrderlistMultiid;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor
public class Orderlist implements Serializable {

    @EmbeddedId
    private OrderlistMultiid orderlistMultiid;

    private Long count;

    @MapsId("ordersUid")
    @ManyToOne
    @JoinColumn(name = "ORDER_UID")
    private Order order;


    @MapsId("brickUid")
    @ManyToOne
    @JoinColumn(name = "BRICK_UID")
    private Brick brick;

    @Builder
    public Orderlist(OrderlistMultiid orderlistMultiid, Long count, Order order, Brick brick){
        this.orderlistMultiid = orderlistMultiid;
        this.count = count;
        this.order = order;
        this.brick = brick;
    }
}

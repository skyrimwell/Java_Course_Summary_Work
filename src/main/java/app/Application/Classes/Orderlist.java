package app.Application.Classes;

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
    @JoinColumn(name = "ORDERS_UID")
    private Order orders;


    @MapsId("brickUid")
    @ManyToOne
    @JoinColumn(name = "BRICK_UID")
    private Brick brick;

    @Builder
    public Orderlist(OrderlistMultiid orderlistMultiid, Long count, Order orders, Brick brick){
        this.orderlistMultiid = orderlistMultiid;
        this.count = count;
        this.orders = orders;
        this.brick = brick;
    }
}

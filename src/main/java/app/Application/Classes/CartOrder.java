package app.Application.Classes;

import app.Application.Classes.Cart;
import app.Application.Classes.Brick;
import app.Application.Misc.MultiId;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor
public class CartOrder implements Serializable{
    @EmbeddedId
    private MultiId multiId;

    private Long brickCount;

    @MapsId("cartUid")
    @ManyToOne
    @JoinColumn(name = "CART_UID")
    private Cart cart;

    @MapsId("brickUid")
    @ManyToOne
    @JoinColumn(name = "BRICK_UID")
    private Brick brick;


    @Builder
    public CartOrder(MultiId multiId, Long brickCount, Cart cart,Brick brick){
        this.multiId = multiId;
        this.brickCount = brickCount;
        this.brick = brick;
        this.cart = cart;
    }
}

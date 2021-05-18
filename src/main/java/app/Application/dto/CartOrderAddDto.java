package app.Application.dto;

import app.Application.Classes.Brick;
import app.Application.Classes.Cart;
import app.Application.Classes.CartOrder;
import app.Application.Misc.MultiId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartOrderAddDto {
    private MultiId multiId;
    private Long brickCount;
    private Cart cart;
    private Brick brick;

    public CartOrder toEntity(){
        return CartOrder.builder()
                .multiId(multiId)
                .brickCount(brickCount)
                .brick(brick)
                .cart(cart)
                .build();
    }
}

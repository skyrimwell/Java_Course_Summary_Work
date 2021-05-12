package app.Application.dto;

import app.Application.Classes.Cart;
import app.Application.Classes.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartCreateDto {
    private User user;
    private String createtime;
    private String modifytime;

    public Cart toEntity(){
        return Cart.builder()
                .user(user)
                .createtime(createtime)
                .modifytime(modifytime)
                .build();
    }
}

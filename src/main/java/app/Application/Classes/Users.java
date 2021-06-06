package app.Application.Classes;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Users implements Serializable {

    @Id
    private String id;

    private String pw;

    private String name;

    @OneToMany(mappedBy = "users", cascade = {CascadeType.REMOVE})
    private List<Card> card;

    @OneToMany(mappedBy = "users", cascade = {CascadeType.REMOVE})
    private List<Cart> cart;

    @OneToMany(mappedBy = "users", orphanRemoval = true)
    private List<Orders> orders;

    @Builder
    public Users(String id, String pw, String name, List<Card> card, List<Cart> cart, List<Orders> orders){
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.card = card;
        this.cart = cart;
        this.orders = orders;
    }
}
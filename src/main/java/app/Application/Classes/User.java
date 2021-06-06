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
public class User implements Serializable {

    @Id
    private String id;

    private String pw;

    private String name;

    @OneToMany(mappedBy = "users", cascade = {CascadeType.REMOVE})
    private List<Card> card;

    @OneToMany(mappedBy = "users", cascade = {CascadeType.REMOVE})
    private List<Cart> cart;

    @OneToMany(mappedBy = "users", orphanRemoval = true)
    private List<Order> orders;

    @Builder
    public User(String id, String pw, String name, List<Card> card, List<Cart> cart, List<Order> orders){
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.card = card;
        this.cart = cart;
        this.orders = orders;
    }
}
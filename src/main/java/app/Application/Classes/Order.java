package app.Application.Classes;

import app.Application.Classes.User;
import app.Application.Classes.Orderlist;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Getter
@Entity
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private User user;

    private String date;

    private Long amount;

    private String cardId;

    private String cardType;

    private String cardDate;

    private Long shippingNum;

    @OneToMany(mappedBy = "order", orphanRemoval = true)
    private List<Orderlist> orderlist;


    @Builder
    public Order(Long uid, User user, String date, Long amount, String cardId, String cardType, String cardDate, List<Orderlist> orderlist){
        this.uid= uid;
        this.user = user;
        this.date = date;
        this.amount = amount;
        this.cardId = cardId;
        this.cardType = cardType;
        this.cardDate= cardDate;
        this.orderlist = orderlist;
    }

}

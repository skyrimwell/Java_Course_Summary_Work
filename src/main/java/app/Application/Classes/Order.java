package app.Application.Classes;

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
    private User users;

    private String date;

    private Long amount;

    private String cardId;

    private String cardType;

    private String cardDate;

    @OneToMany(mappedBy = "orders", orphanRemoval = true)
    private List<Orderlist> orderlists;


    @Builder
    public Order(Long uid, User users, String date, Long amount, String cardId, String cardType, String cardDate, List<Orderlist> orderlists){
        this.uid= uid;
        this.users = users;
        this.date = date;
        this.amount = amount;
        this.cardId = cardId;
        this.cardType = cardType;
        this.cardDate= cardDate;
        this.orderlists = orderlists;
    }

}

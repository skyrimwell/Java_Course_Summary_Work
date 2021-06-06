package app.Application.Classes;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users users;

    private String createtime;

    private String modifytime;


    @OneToMany(mappedBy = "cart", cascade = {CascadeType.REMOVE})
    private List<CartOrder> cartOrders;

    @Builder
    public Cart(Long uid, Users users, String createtime, String modifytime, List<CartOrder> cartOrders){
        this.uid = uid;
        this.users = users;
        this.createtime = createtime;
        this.modifytime = modifytime;
        this.cartOrders = cartOrders;
    }

    public void updateModifytime(String modifytime){
        this.modifytime = modifytime;
    }

}

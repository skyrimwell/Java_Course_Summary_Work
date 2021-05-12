package app.Application.Classes;

import app.Application.Classes.User;
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
    private User user;

    private String createtime;

    private String modifytime;


    @Builder
    public Cart(Long uid, User user, String createtime, String modifytime){
        this.uid = uid;
        this.user = user;
        this.createtime = createtime;
        this.modifytime = modifytime;
    }

    public void updateModifytime(String modifytime){
        this.modifytime = modifytime;
    }

}

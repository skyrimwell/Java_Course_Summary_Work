package app.Application.Classes;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor
@Entity
public class Card {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users users;

    private String datetime;

    private String type;

    @Builder
    public Card(String id, Users users, String datetime, String type){
        this.id = id;
        this.users = users;
        this.datetime = datetime;
        this.type = type;
    }
}

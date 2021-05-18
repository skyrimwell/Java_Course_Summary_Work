package app.Application.Classes;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Builder
    public User(String id, String pw, String name){
        this.id = id;
        this.pw = pw;
        this.name = name;
    }
}
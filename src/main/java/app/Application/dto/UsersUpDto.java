package app.Application.dto;
import app.Application.Classes.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersUpDto {
    private String id;
    private String pw;
    private String name;

    @Builder
    public UsersUpDto(String id, String pw, String name){
        this.id = id;
        this.pw = pw;
        this.name = name;
    }

    public User toEntity(){
        return User.builder()
                .id(id).pw(pw).name(name).build();
    }
}

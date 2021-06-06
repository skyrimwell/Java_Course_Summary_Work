package app.Application.dto;
import app.Application.Classes.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersInDto {
    private String id;
    private String pw;

    @Builder
    public UsersInDto(String id, String pw){
        this.id = id;
        this.pw = pw;
    }

    public User toEntity(){
        return User.builder()
                .id(id).pw(pw).build();
    }
}

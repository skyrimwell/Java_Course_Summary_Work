package app.Application.dto;
import app.Application.Classes.Users;
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

    public Users toEntity(){
        return Users.builder()
                .id(id).pw(pw).build();
    }
}

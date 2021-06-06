package app.Application.dto;
import app.Application.Classes.Brick;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BrickDeleteDto {

    private Long uid;
    private String brickType;
    private double brickSize;
    private String brickColor;

    public Brick toEntity(){
        return Brick.builder().uid(uid).brickType(brickType).brickSize(brickSize).brickColor(brickColor).build();
    }
}

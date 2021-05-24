package app.Application.dto;
import app.Application.Classes.Brick;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BrickUpdateDto {

    private UUID uid;
    private String type;
    private double size;
    private String color;
    private Long brickcount;
    private int price;

    public Brick toEntity(){
        return Brick.builder().uid(uid).type(type).size(size).color(color).count(brickcount).price(price).build();
    }
}

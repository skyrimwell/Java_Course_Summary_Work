package app.Application.dto;
import app.Application.Classes.Brick;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BrickSaveDto {
    private String type;
    private double size;
    private String color;

    @Builder
    public BrickSaveDto(String type, double size, String color) {
        this.type = type;
        this.size = size;
        this.color = color;
    }

    public Brick toEntity(){
        return Brick.builder().type(type).size(size).color(color).build();
    }
}

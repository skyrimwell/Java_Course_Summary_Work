package app.Application.dto;
import app.Application.Classes.Brick;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class BrickSaveDto {
    private String brickType;
    private double brickSize;
    private String brickColor;
    private Long brickCount;
    private int brickPrice;

    @Builder
    public BrickSaveDto(String brickType, double brickSize, String brickColor, int brickPrice, Long brickCount) {
        this.brickType = brickType;
        this.brickSize = brickSize;
        this.brickColor = brickColor;
        this.brickPrice = brickPrice;
        this.brickCount = brickCount;
    }

    public Brick toEntity(){
        return Brick.builder().brickType(brickType).brickSize(brickSize).brickColor(brickColor).brickPrice(brickPrice).brickCount(brickCount).build();
    }
}

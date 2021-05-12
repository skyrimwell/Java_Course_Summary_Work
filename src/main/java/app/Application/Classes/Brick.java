package app.Application.Classes;

import app.Application.dto.BrickUpdateDto;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "bricks")
public class Brick implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private UUID uid;

    @Column(name = "type")
    @NotNull
    private String type;

    @Column(name = "size")
    @NotNull
    private double size;

    @Column(name = "color")
    @NotNull
    private String color;

    @Column(name = "count")
    @NotNull
    private int count;

    @Column(name = "price")
    @NotNull
    private int price;

    @Builder
    public Brick(UUID uid, String type, double size, String color, int count, int price) {
        this.uid = uid;
        this.type = type;
        this.size = size;
        this.color = color;
        this.count = count;
        this.price = price;
    }

    public void updateBrick(BrickUpdateDto brickUpdateDTO){
        this.uid = brickUpdateDTO.getUid();
        this.type = brickUpdateDTO.getType();
        this.size = brickUpdateDTO.getSize();
        this.color = brickUpdateDTO.getColor();
    }

    @Override
    public String toString() {
        return "Brick with this params: " + uid + " Type: " + type + " Size:  " + size + "Color: " + color;
    }
}

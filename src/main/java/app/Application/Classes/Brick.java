package app.Application.Classes;

import app.Application.dto.BrickUpdateCountDto;
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
public class Brick implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BRICK_UID")
    private Long uid;

    @Column(name = "brickType")
    @NotNull
    private String brickType;

    @Column(name = "brickSize")
    @NotNull
    private double brickSize;

    @Column(name = "brickColor")
    @NotNull
    private String brickColor;

    @Column(name = "brickCount")
    @NotNull
    private Long brickCount;

    @Column(name = "brickPrice")
    @NotNull
    private int brickPrice;

    @OneToMany(mappedBy = "brick", cascade = {CascadeType.REMOVE})
    private List<CartOrder> cartOrders;

    @OneToMany(mappedBy = "brick", orphanRemoval = true)
    private List<Orderlist> orderlists;
    @Builder

    public Brick(Long uid, String brickType, Double brickSize, String brickColor, Long brickCount, int brickPrice, List<CartOrder> cartOrders, List<Orderlist> orderlists) {
        this.uid = uid;
        this.brickType = brickType;
        this.brickSize = brickSize;
        this.brickColor = brickColor;
        this.brickCount = brickCount;
        this.brickPrice = brickPrice;
        this.cartOrders = cartOrders;
        this.orderlists = orderlists;
    }

    public void updateBrick(BrickUpdateDto brickUpdateDTO){
        this.uid = brickUpdateDTO.getUid();
        this.brickType = brickUpdateDTO.getBrickType();
        this.brickSize = brickUpdateDTO.getBrickSize();
        this.brickColor = brickUpdateDTO.getBrickColor();
    }

    public void updateCount(BrickUpdateCountDto brickUpdateCountDto){
        this.brickCount = brickUpdateCountDto.getBrickCount();
    }

}

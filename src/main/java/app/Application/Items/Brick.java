package app.Application.Items;


import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "bricks")
public class Brick implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "type")
    @NotNull
    private String type;
    //type = name
    @Column(name = "size")
    @NotNull
    private double size;
    //size = owner

    @Column(name = "color")
    @NotNull
    private String color;
    //size = owner

    public Brick() {
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOwner(double size) {
        this.size = size;
    }

    public Brick(String type, double size, String color) {
        this.type = type;
        this.size = size;
        this.color = color;
    }


    public UUID getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public double getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Brick with this params: " + id + " Type: " + type + " Size:  " + size + "Color: " + color;
    }
}

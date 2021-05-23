package app.Application.Classes;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class OrderlistMultiid implements Serializable {

    private Long ordersUid;

    private Long brickUid;
}

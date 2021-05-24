package app.Application.Misc;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Data
@Embeddable
public class MultiId implements Serializable {

    private Long cartUid;

    private UUID brickUid;
}


package finalproject.domain;

import finalproject.domain.*;
import finalproject.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OutOfTicket extends AbstractEvent {

    private Long id;
    private Long reserveId;
}

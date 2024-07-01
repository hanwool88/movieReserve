package finalprojcet.domain;

import finalprojcet.domain.*;
import finalprojcet.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OutOfTicket extends AbstractEvent {

    private Long id;
    private Long reserveId;
}

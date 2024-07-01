package finalprojcet.domain;

import finalprojcet.domain.*;
import finalprojcet.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ReserveCanceled extends AbstractEvent {

    private Long id;
    private String userId;
    private String reserveStatus;
    private Integer amount;
    private Date reserveDate;
    private Long movieId;
}

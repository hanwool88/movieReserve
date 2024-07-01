package finalproject.domain;

import finalproject.domain.*;
import finalproject.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ReserveCanceled extends AbstractEvent {

    private Long id;
    private String userId;
    private String reserveStatus;
    private Integer amount;
    private Date reserveDate;
    private Long movieId;

    public ReserveCanceled(Reserve aggregate) {
        super(aggregate);
    }

    public ReserveCanceled() {
        super();
    }
}
//>>> DDD / Domain Event

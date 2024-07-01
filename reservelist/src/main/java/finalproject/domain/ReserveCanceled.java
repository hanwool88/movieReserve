package finalproject.domain;

import finalproject.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class ReserveCanceled extends AbstractEvent {

    private Long id;
    private String userId;
    private String reserveStatus;
    private Integer amount;
    private Date reserveDate;
    private Long movieId;
}

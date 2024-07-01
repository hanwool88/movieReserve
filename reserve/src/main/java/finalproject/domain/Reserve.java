package finalproject.domain;

import finalproject.ReserveApplication;
import finalproject.domain.ReserveCanceled;
import finalproject.domain.Reserved;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Reserve_table")
@Data
//<<< DDD / Aggregate Root
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private String reserveStatus;

    private Integer amount;

    private Date reserveDate;

    private Long movieId;

    @PostPersist
    public void onPostPersist() {
        Reserved reserved = new Reserved(this);
        reserved.publishAfterCommit();

        ReserveCanceled reserveCanceled = new ReserveCanceled(this);
        reserveCanceled.publishAfterCommit();
    }

    public static ReserveRepository repository() {
        ReserveRepository reserveRepository = ReserveApplication.applicationContext.getBean(
            ReserveRepository.class
        );
        return reserveRepository;
    }

    //<<< Clean Arch / Port Method
    public static void updateStatus(OutOfTicket outOfTicket) {
        repository().findById(outOfTicket.getReserveId()).ifPresent(reserve ->{
            
            reserve.setReserveStatus("reserveCancelled");
            repository().save(reserve);
        });

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root

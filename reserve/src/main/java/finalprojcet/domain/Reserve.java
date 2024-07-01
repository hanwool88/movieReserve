package finalprojcet.domain;

import finalprojcet.ReserveApplication;
import finalprojcet.domain.Reserved;
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
    }

    public static ReserveRepository repository() {
        ReserveRepository reserveRepository = ReserveApplication.applicationContext.getBean(
            ReserveRepository.class
        );
        return reserveRepository;
    }

    //<<< Clean Arch / Port Method
    public void reserveCancel() {
        //implement business logic here:

        ReserveCanceled reserveCanceled = new ReserveCanceled(this);
        reserveCanceled.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void updateStatus(OutOfTicket outOfTicket) {
        //implement business logic here:

        /** Example 1:  new item 
        Reserve reserve = new Reserve();
        repository().save(reserve);

        ReserveCanceled reserveCanceled = new ReserveCanceled(reserve);
        reserveCanceled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(outOfTicket.get???()).ifPresent(reserve->{
            
            reserve // do something
            repository().save(reserve);

            ReserveCanceled reserveCanceled = new ReserveCanceled(reserve);
            reserveCanceled.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root

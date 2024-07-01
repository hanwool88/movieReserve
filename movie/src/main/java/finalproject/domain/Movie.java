package finalproject.domain;

import finalproject.MovieApplication;
import finalproject.domain.OutOfTicket;
import finalproject.domain.TicketDecreased;
import finalproject.domain.TicketIncreased;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import org.springframework.util.StringUtils;

import lombok.Data;

@Entity
@Table(name = "Movie_table")
@Data
//<<< DDD / Aggregate Root
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    private Integer stock;

    private String movieName;

    @PostPersist
    public void onPostPersist() {
        TicketDecreased ticketDecreased = new TicketDecreased(this);
        ticketDecreased.publishAfterCommit();

        TicketIncreased ticketIncreased = new TicketIncreased(this);
        ticketIncreased.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        OutOfTicket outOfTicket = new OutOfTicket(this);
        outOfTicket.publishAfterCommit();
    }

    public static MovieRepository repository() {
        MovieRepository movieRepository = MovieApplication.applicationContext.getBean(
            MovieRepository.class
        );
        return movieRepository;
    }

    //<<< Clean Arch / Port Method
    public static void decreaseTicket(Reserved reserved) {

        repository().findById(reserved.getMovieId()).ifPresent(movie->{
            
            if(movie.getStock() >= reserved.getAmount()) {
                movie.setStock(movie.getStock() - reserved.getAmount());
                repository().save(movie);
                
                TicketDecreased ticketDecreased = new TicketDecreased(movie);
                ticketDecreased.publishAfterCommit();
            }
            else {
                OutOfTicket outOfTicket = new OutOfTicket(movie);
                outOfTicket.setReserveId(reserved.getId());
                outOfTicket.publishAfterCommit();
            }

         });
        

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void increaseTicket(ReserveCanceled reserveCanceled) {
        
        repository().findById(reserveCanceled.getMovieId()).ifPresent(movie->{
            
            if(reserveCanceled.getReserveDate().compareTo(movie.getDate()) > 0) {
                return;
            }
            else {
                movie.setStock(movie.getStock() + reserveCanceled.getAmount());
                repository().save(movie);
    
                TicketIncreased ticketIncreased = new TicketIncreased(movie);
                ticketIncreased.publishAfterCommit();
            }

         });
        

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root

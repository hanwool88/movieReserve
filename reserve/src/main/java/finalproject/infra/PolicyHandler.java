package finalproject.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import finalproject.config.kafka.KafkaProcessor;
import finalproject.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    ReserveRepository reserveRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OutOfTicket'"
    )
    public void wheneverOutOfTicket_UpdateStatus(
        @Payload OutOfTicket outOfTicket
    ) {
        OutOfTicket event = outOfTicket;
        System.out.println(
            "\n\n##### listener UpdateStatus : " + outOfTicket + "\n\n"
        );

        // Sample Logic //
        Reserve.updateStatus(event);
    }
}
//>>> Clean Arch / Inbound Adaptor

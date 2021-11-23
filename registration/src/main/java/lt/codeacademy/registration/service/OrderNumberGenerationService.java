package lt.codeacademy.registration.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class OrderNumberGenerationService {

    private Long generate (){
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        Long date = zdt.toInstant().toEpochMilli();
        return date;
    }
}

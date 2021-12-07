package lt.codeacademy.registration.service.email;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.registration.model.RepairOrder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConfirmationEmailService {

    private final JavaMailSender javaMailSender;
    private final OrderMessage orderMessage;

    public String sendEmail(RepairOrder repairOrder) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("repairprojektas@gmail.com");
        message.setTo(repairOrder.getCustomer().getEmail());
        message.setSubject("Repair ChopShop Registration Confirmation");
        message.setText(orderMessage.orderMessage(repairOrder));

        javaMailSender.send(message);

        return "Email was sent successfully ";
    }
}

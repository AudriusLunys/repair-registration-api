package lt.codeacademy.registration.service;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.registration.model.RepairOrder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderConfirmationEmailService {

    private final JavaMailSender javaMailSender;

    public String sendEmail(RepairOrder repairOrder) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("repairprojektas@gmail.com");
        message.setTo(repairOrder.getCustomer().getEmail());
        message.setSubject("Repair ChopShop Registration Confirmation");
        message.setText("Hello, This is  your registration order details" + "\n" +
                "Order number: " + repairOrder.getRegistrationNr() + "\n" +
                "Registration date: " + repairOrder.getRegistrationDate() + "\n" +
                "Device: " + repairOrder.getDevice().getManufacturer() + " " + repairOrder.getDevice().getModel() + "\n" +
                "Failure description: " + repairOrder.getDevice().getFailureDescription() + "\n" +
                "\n" +
                "Provided registration number " + repairOrder.getRegistrationNr() + " will be needed to retrieve the device" +
                "\n" +
                "\n" +
                "Best regards" + "\n" + "Repair ChopShop team"
        );

        javaMailSender.send(message);

        return "Email was sent successfully ";
    }
}

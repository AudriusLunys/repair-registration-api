package lt.codeacademy.registration.service.email;

import lt.codeacademy.registration.model.RepairOrder;
import org.springframework.stereotype.Component;

@Component
public class OrderMessage {

    public String orderMessage (RepairOrder repairOrder){
       return "Hello," + repairOrder.getCustomer().getFirstName() + " "
                + repairOrder.getCustomer().getLastName() + " "
                + "This is  your registration order details" + "\n" +
                "Order number: " + repairOrder.getRegistrationNr() + "\n" +
                "Registration date: " + repairOrder.getRegistrationDate() + "\n" +
                "Device: " + repairOrder.getDevice().getManufacturer() + " " + repairOrder.getDevice().getModel() + "\n" +
                "Failure description: " + repairOrder.getDevice().getFailureDescription() + "\n" +
                "\n" +
                "Provided registration number " + repairOrder.getRegistrationNr() + " will be needed to retrieve the device" +
                "\n" +
                "\n" +
                "Best regards" + "\n" + "Repair ChopShop team";
    }
}

package lt.codeacademy.registration.service;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.registration.model.Customer;
import lt.codeacademy.registration.model.RepairOrder;
import lt.codeacademy.registration.repository.RepairOrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Transactional
@Service
@RequiredArgsConstructor
public class RepairOrderService {

    private final RepairOrderRepository repairOrderRepository;
    private final OrderNumberGenerationService generationService;
    private final CustomerService customerService;

    public List<RepairOrder> findAll() {
        return repairOrderRepository.findAll();
    }

    public Optional<RepairOrder> findByRegistrationNr(Long registrationNr) {
        return repairOrderRepository.findRepairOrderByRegistrationNr(registrationNr);
    }

    public RepairOrder saveRepairOrder(RepairOrder repairOrder) {
        List<Customer> customersFromDb = customerService.findAll();
        var a = customersFromDb.stream().filter(customer -> customer.getFirstName().equalsIgnoreCase(repairOrder.getCustomer().getFirstName()) &&
                        customer.getLastName().equalsIgnoreCase(repairOrder.getCustomer().getLastName()) &&
                        customer.getEmail().equalsIgnoreCase(repairOrder.getCustomer().getEmail()) &&
                        customer.getTelNumber().equalsIgnoreCase(repairOrder.getCustomer().getTelNumber()))
                .collect(Collectors.toList());
        if (a.isEmpty()) {
            Customer customer = new Customer();
            customer.setFirstName(repairOrder.getCustomer().getFirstName());
            customer.setLastName(repairOrder.getCustomer().getLastName());
            customer.setEmail(repairOrder.getCustomer().getEmail());
            customer.setTelNumber(repairOrder.getCustomer().getTelNumber());
            repairOrder.setCustomer(customer);
        } else {
            repairOrder.setCustomer(a.get(0));
        }
        repairOrder.setRegistrationNr(generationService.generate());
        repairOrder.setRegistrationDate(LocalDate.now());
        return repairOrderRepository.save(repairOrder);
    }

    public void deleteOrder(Long registrationNr) {
        repairOrderRepository.deleteByRegistrationNr(registrationNr);
    }
}

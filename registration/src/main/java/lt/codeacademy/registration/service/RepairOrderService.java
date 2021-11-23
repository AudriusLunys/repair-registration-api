package lt.codeacademy.registration.service;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.registration.model.Customer;
import lt.codeacademy.registration.model.RepairOrder;
import lt.codeacademy.registration.repository.RepairOrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
@RequiredArgsConstructor
public class RepairOrderService {

    private final RepairOrderRepository repairOrderRepository;

    public List<RepairOrder> findAll() {
        return repairOrderRepository.findAll();
    }
    public Optional<RepairOrder> findByRegistrationNr(Long registrationNr) {
        return repairOrderRepository.findRepairOrderByRegistrationNr(registrationNr);
    }
}

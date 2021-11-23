package lt.codeacademy.registration.service;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.registration.model.RepairOrder;
import lt.codeacademy.registration.repository.RepairOrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Transactional
@Service
@RequiredArgsConstructor
public class RepairOrderService {

    private final RepairOrderRepository repairOrderRepository;
    private final OrderNumberGenerationService generationService;
    private final CustomerService customerService;
    private final DeviceService deviceService;

    public List<RepairOrder> findAll() {
        return repairOrderRepository.findAll();
    }
    public Optional<RepairOrder> findByRegistrationNr(Long registrationNr) {
        return repairOrderRepository.findRepairOrderByRegistrationNr(registrationNr);
    }
    public RepairOrder saveRepairOrder(RepairOrder repairOrder) {
        repairOrder.setRegistrationNr(generationService.generate());
        repairOrder.setRegistrationDate(LocalDate.now());
        return repairOrderRepository.save(repairOrder);
    }
}

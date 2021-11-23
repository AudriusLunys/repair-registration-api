package lt.codeacademy.registration.repository;


import lt.codeacademy.registration.model.RepairOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RepairOrderRepository extends JpaRepository<RepairOrder, Long> {

    Optional<RepairOrder> findRepairOrderByRegistrationNr(Long registrationNr);
}

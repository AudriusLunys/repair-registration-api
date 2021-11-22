package lt.codeacademy.registration.repository;


import lt.codeacademy.registration.model.RepairOrder;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RepairOrderRepository extends JpaRepository<RepairOrder,Long> {

}

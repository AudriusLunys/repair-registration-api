package lt.codeacademy.registration.repository;

import lt.codeacademy.registration.model.Customer;
import lt.codeacademy.registration.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findByUuid(UUID uuid);

    void deleteByUuid (UUID uuid);

}

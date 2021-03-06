package lt.codeacademy.registration.repository;

import lt.codeacademy.registration.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, UUID> {

    Optional<Device> findByUuid(UUID uuid);

    void deleteByUuid (UUID uuid);
}

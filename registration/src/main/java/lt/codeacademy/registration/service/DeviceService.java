package lt.codeacademy.registration.service;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.registration.model.Device;
import lt.codeacademy.registration.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    public Optional<Device> findByUuid(UUID uuid) {
        return deviceRepository.findByUuid(uuid);
    }
}

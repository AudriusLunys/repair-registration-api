package lt.codeacademy.registration.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lt.codeacademy.registration.model.Device;
import lt.codeacademy.registration.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping(value = "/device", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Get list of devices", httpMethod = "GET")
    public List<Device> getDeviceList() {
        return deviceService.findAll();
    }

    @GetMapping(value = "/device/{uuid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Get device by UUID", httpMethod = "GET")
    public ResponseEntity<?> getDeviceById(@PathVariable UUID uuid) {
        Optional<Device> device = deviceService.findByUuid(uuid);
        return device.map(response -> ResponseEntity.ok().body(response)).orElse
                (new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
}

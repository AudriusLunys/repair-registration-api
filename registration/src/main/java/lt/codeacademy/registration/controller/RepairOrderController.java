package lt.codeacademy.registration.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lt.codeacademy.registration.model.RepairOrder;
import lt.codeacademy.registration.service.RepairOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class RepairOrderController {

    private final RepairOrderService repairOrderService;

    @GetMapping(value = "/order", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Get list of registered repair orders", httpMethod = "GET")
    public List<RepairOrder> getOrderList() {
        return repairOrderService.findAll();
    }

    @GetMapping(value = "/order/{registrationNr}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Get order by order number", httpMethod = "GET")
    public ResponseEntity<?> getorderByOrderNr(@PathVariable Long registrationNr) {
        Optional<RepairOrder> repairOrder = repairOrderService.findByRegistrationNr(registrationNr);
        return repairOrder.map(response -> ResponseEntity.ok().body(response)).orElse
                (new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

package lt.codeacademy.registration.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lt.codeacademy.registration.model.RepairOrder;
import lt.codeacademy.registration.service.OrderConfirmationEmailService;
import lt.codeacademy.registration.service.RepairOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;



@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class RepairOrderController {

    private final RepairOrderService repairOrderService;
    private final OrderConfirmationEmailService emailService;

    @GetMapping(value = "/order", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Get list of registered repair orders", httpMethod = "GET")
    public List<RepairOrder> getOrderList() {
        return repairOrderService.findAll();
    }

    @GetMapping(value = "/order/{registrationNr}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Get order by order number", httpMethod = "GET")
    public ResponseEntity<?> getOrderByOrderNr(@PathVariable Long registrationNr) {
        Optional<RepairOrder> repairOrder = repairOrderService.findByRegistrationNr(registrationNr);
        return repairOrder.map(response -> ResponseEntity.ok().body(response)).orElse
                (new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/order")
    @ApiOperation(value = "Create repair order", httpMethod = "POST")
    public ResponseEntity<Void> createRepairOrder(@Valid @RequestBody RepairOrder repairOrder) {
        repairOrderService.saveRepairOrder(repairOrder);
        emailService.sendEmail(repairOrder);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/order/{registrationNr}")
    @ApiOperation(value = "Delete repair order by registration number", httpMethod = "DELETE")
    public ResponseEntity<?> deleteDevice(@PathVariable Long registrationNr) {
        repairOrderService.deleteOrder(registrationNr);
        return ResponseEntity.ok().build();
    }
}

package lt.codeacademy.registration.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lt.codeacademy.registration.model.RepairOrder;
import lt.codeacademy.registration.service.email.OrderConfirmationEmailService;
import lt.codeacademy.registration.service.RepairOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;



@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class RepairOrderController {

    private final RepairOrderService repairOrderService;
    private final OrderConfirmationEmailService emailService;

    @GetMapping(value = "/order", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Get list of registered repair orders with pagination and sorting", httpMethod = "GET")
    public Page<RepairOrder> getOrderList(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
    ) {
        return repairOrderService.getOrdersPaginated(
                PageRequest.of(
                        page.orElse(0), 5,
                        Sort.Direction.DESC, sortBy.orElse("registrationDate")
                )
        );
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

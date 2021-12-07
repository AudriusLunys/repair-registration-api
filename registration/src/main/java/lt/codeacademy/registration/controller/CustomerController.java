package lt.codeacademy.registration.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lt.codeacademy.registration.model.Customer;
import lt.codeacademy.registration.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(value = "/customer", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Get list of customers", httpMethod = "GET")
    public List<Customer> getCustomerList() {
        return customerService.findAll();
    }

    @GetMapping(value = "/customer/{uuid}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Get customer by UUID", httpMethod = "GET")
    public ResponseEntity<?> getCustomerById(@PathVariable UUID uuid) {
        Optional<Customer> customer = customerService.findByUuid(uuid);
        return customer.map(response -> ResponseEntity.ok().body(response)).orElse
                (new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/customer")
    @ApiOperation(value = "Create customer", httpMethod = "POST")
    public ResponseEntity<Void> createCustomer(@Valid @RequestBody Customer customer) {
        customer.setUuid(UUID.randomUUID());
        customerService.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/customer/{uuid}")
    @ApiOperation(value = "Update customer ", httpMethod = "PUT")
    public ResponseEntity<Void> updateCustomer(@PathVariable UUID uuid, @Valid @RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("/customer/{uuid}")
    @ApiOperation(value = "Delete customer by UUID", httpMethod = "DELETE")
    public ResponseEntity<?> deleteCustomer(@PathVariable UUID uuid) {
        customerService.deleteCustomer(uuid);
        return ResponseEntity.ok().build();
    }
}

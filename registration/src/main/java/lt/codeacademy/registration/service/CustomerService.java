package lt.codeacademy.registration.service;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.registration.model.Customer;
import lt.codeacademy.registration.model.Device;
import lt.codeacademy.registration.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findByUuid(UUID uuid) {
        return customerRepository.findByUuid(uuid);
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(UUID uuid) {
       customerRepository.deleteByUuid(uuid);
    }

}

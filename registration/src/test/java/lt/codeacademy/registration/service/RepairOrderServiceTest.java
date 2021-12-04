package lt.codeacademy.registration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import lt.codeacademy.registration.model.Customer;
import lt.codeacademy.registration.model.Device;
import lt.codeacademy.registration.model.RepairOrder;
import lt.codeacademy.registration.repository.RepairOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RepairOrderService.class})
@ExtendWith(SpringExtension.class)
class RepairOrderServiceTest {
    @MockBean
    private CustomerService customerService;

    @MockBean
    private OrderNumberGenerationService orderNumberGenerationService;

    @MockBean
    private RepairOrderRepository repairOrderRepository;

    @Autowired
    private RepairOrderService repairOrderService;

    @Test
    void testSaveRepairOrder() {
        Device device = new Device();
        device.setModel("Model");
        device.setFailureDescription("Failure Description");
        device.setId(123L);
        device.setSerialNumber("42");
        device.setUuid(UUID.randomUUID());
        device.setManufacturer("Manufacturer");

        Customer customer = new Customer();
        customer.setLastName("Doe");
        customer.setEmail("jane.doe@example.org");
        customer.setTelNumber("42");
        customer.setId(123L);
        customer.setUuid(UUID.randomUUID());
        customer.setFirstName("Jane");

        RepairOrder repairOrder = new RepairOrder();
        repairOrder.setRegistrationDate(LocalDate.ofEpochDay(1L));
        repairOrder.setDevice(device);
        repairOrder.setId(123L);
        repairOrder.setRegistrationNr(1L);
        repairOrder.setCustomer(customer);
        when(this.repairOrderRepository.save((RepairOrder) any())).thenReturn(repairOrder);
        when(this.orderNumberGenerationService.generate()).thenReturn(1L);
        when(this.customerService.findAll()).thenReturn(new ArrayList<Customer>());

        Device device1 = new Device();
        device1.setModel("Model");
        device1.setFailureDescription("Failure Description");
        device1.setId(123L);
        device1.setSerialNumber("42");
        device1.setUuid(UUID.randomUUID());
        device1.setManufacturer("Manufacturer");

        Customer customer1 = new Customer();
        customer1.setLastName("Doe");
        customer1.setEmail("jane.doe@example.org");
        customer1.setTelNumber("42");
        customer1.setId(123L);
        customer1.setUuid(UUID.randomUUID());
        customer1.setFirstName("Jane");

        RepairOrder repairOrder1 = new RepairOrder();
        repairOrder1.setRegistrationDate(LocalDate.ofEpochDay(1L));
        repairOrder1.setDevice(device1);
        repairOrder1.setId(123L);
        repairOrder1.setRegistrationNr(1L);
        repairOrder1.setCustomer(customer1);
        assertSame(repairOrder, this.repairOrderService.saveRepairOrder(repairOrder1));
        verify(this.repairOrderRepository).save((RepairOrder) any());
        verify(this.orderNumberGenerationService).generate();
        verify(this.customerService).findAll();
        assertEquals(1L, repairOrder1.getRegistrationNr().longValue());
        Customer customer2 = repairOrder1.getCustomer();
        assertEquals("Doe", customer2.getLastName());
        assertEquals("Jane", customer2.getFirstName());
        assertEquals("jane.doe@example.org", customer2.getEmail());
        assertEquals("42", customer2.getTelNumber());
    }

    @Test
    void testSaveRepairOrder2() {
        Device device = new Device();
        device.setModel("Model");
        device.setFailureDescription("Failure Description");
        device.setId(123L);
        device.setSerialNumber("42");
        device.setUuid(UUID.randomUUID());
        device.setManufacturer("Manufacturer");

        Customer customer = new Customer();
        customer.setLastName("Doe");
        customer.setEmail("jane.doe@example.org");
        customer.setTelNumber("42");
        customer.setId(123L);
        customer.setUuid(UUID.randomUUID());
        customer.setFirstName("Jane");

        RepairOrder repairOrder = new RepairOrder();
        repairOrder.setRegistrationDate(LocalDate.ofEpochDay(1L));
        repairOrder.setDevice(device);
        repairOrder.setId(123L);
        repairOrder.setRegistrationNr(1L);
        repairOrder.setCustomer(customer);
        when(this.repairOrderRepository.save((RepairOrder) any())).thenReturn(repairOrder);
        when(this.orderNumberGenerationService.generate()).thenReturn(1L);

        Customer customer1 = new Customer();
        customer1.setLastName("Doe");
        customer1.setEmail("jane.doe@example.org");
        customer1.setTelNumber("42");
        customer1.setId(123L);
        customer1.setUuid(UUID.randomUUID());
        customer1.setFirstName("Jane");

        ArrayList<Customer> customerList = new ArrayList<Customer>();
        customerList.add(customer1);
        when(this.customerService.findAll()).thenReturn(customerList);

        Device device1 = new Device();
        device1.setModel("Model");
        device1.setFailureDescription("Failure Description");
        device1.setId(123L);
        device1.setSerialNumber("42");
        device1.setUuid(UUID.randomUUID());
        device1.setManufacturer("Manufacturer");

        Customer customer2 = new Customer();
        customer2.setLastName("Doe");
        customer2.setEmail("jane.doe@example.org");
        customer2.setTelNumber("42");
        customer2.setId(123L);
        customer2.setUuid(UUID.randomUUID());
        customer2.setFirstName("Jane");

        RepairOrder repairOrder1 = new RepairOrder();
        repairOrder1.setRegistrationDate(LocalDate.ofEpochDay(1L));
        repairOrder1.setDevice(device1);
        repairOrder1.setId(123L);
        repairOrder1.setRegistrationNr(1L);
        repairOrder1.setCustomer(customer2);
        assertSame(repairOrder, this.repairOrderService.saveRepairOrder(repairOrder1));
        verify(this.repairOrderRepository).save((RepairOrder) any());
        verify(this.orderNumberGenerationService).generate();
        verify(this.customerService).findAll();
        assertSame(customer1, repairOrder1.getCustomer());
        assertEquals(1L, repairOrder1.getRegistrationNr().longValue());
    }

    @Test
    void testSaveRepairOrder3() {
        Device device = new Device();
        device.setModel("Model");
        device.setFailureDescription("Failure Description");
        device.setId(123L);
        device.setSerialNumber("42");
        device.setUuid(UUID.randomUUID());
        device.setManufacturer("Manufacturer");

        Customer customer = new Customer();
        customer.setLastName("Doe");
        customer.setEmail("jane.doe@example.org");
        customer.setTelNumber("42");
        customer.setId(123L);
        customer.setUuid(UUID.randomUUID());
        customer.setFirstName("Jane");

        RepairOrder repairOrder = new RepairOrder();
        repairOrder.setRegistrationDate(LocalDate.ofEpochDay(1L));
        repairOrder.setDevice(device);
        repairOrder.setId(123L);
        repairOrder.setRegistrationNr(1L);
        repairOrder.setCustomer(customer);
        when(this.repairOrderRepository.save((RepairOrder) any())).thenReturn(repairOrder);
        when(this.orderNumberGenerationService.generate()).thenReturn(1L);

        Customer customer1 = new Customer();
        customer1.setLastName("Doe");
        customer1.setEmail("jane.doe@example.org");
        customer1.setTelNumber("42");
        customer1.setId(123L);
        customer1.setUuid(UUID.randomUUID());
        customer1.setFirstName("Jane");

        Customer customer2 = new Customer();
        customer2.setLastName("Doe");
        customer2.setEmail("jane.doe@example.org");
        customer2.setTelNumber("42");
        customer2.setId(123L);
        customer2.setUuid(UUID.randomUUID());
        customer2.setFirstName("Jane");

        ArrayList<Customer> customerList = new ArrayList<Customer>();
        customerList.add(customer2);
        customerList.add(customer1);
        when(this.customerService.findAll()).thenReturn(customerList);

        Device device1 = new Device();
        device1.setModel("Model");
        device1.setFailureDescription("Failure Description");
        device1.setId(123L);
        device1.setSerialNumber("42");
        device1.setUuid(UUID.randomUUID());
        device1.setManufacturer("Manufacturer");

        Customer customer3 = new Customer();
        customer3.setLastName("Doe");
        customer3.setEmail("jane.doe@example.org");
        customer3.setTelNumber("42");
        customer3.setId(123L);
        customer3.setUuid(UUID.randomUUID());
        customer3.setFirstName("Jane");

        RepairOrder repairOrder1 = new RepairOrder();
        repairOrder1.setRegistrationDate(LocalDate.ofEpochDay(1L));
        repairOrder1.setDevice(device1);
        repairOrder1.setId(123L);
        repairOrder1.setRegistrationNr(1L);
        repairOrder1.setCustomer(customer3);
        assertSame(repairOrder, this.repairOrderService.saveRepairOrder(repairOrder1));
        verify(this.repairOrderRepository).save((RepairOrder) any());
        verify(this.orderNumberGenerationService).generate();
        verify(this.customerService).findAll();
        assertSame(customer2, repairOrder1.getCustomer());
        assertEquals(1L, repairOrder1.getRegistrationNr().longValue());
    }

    @Test
    void testSaveRepairOrder4() {
        Device device = new Device();
        device.setModel("Model");
        device.setFailureDescription("Failure Description");
        device.setId(123L);
        device.setSerialNumber("42");
        device.setUuid(UUID.randomUUID());
        device.setManufacturer("Manufacturer");

        Customer customer = new Customer();
        customer.setLastName("Doe");
        customer.setEmail("jane.doe@example.org");
        customer.setTelNumber("42");
        customer.setId(123L);
        customer.setUuid(UUID.randomUUID());
        customer.setFirstName("Jane");

        RepairOrder repairOrder = new RepairOrder();
        repairOrder.setRegistrationDate(LocalDate.ofEpochDay(1L));
        repairOrder.setDevice(device);
        repairOrder.setId(123L);
        repairOrder.setRegistrationNr(1L);
        repairOrder.setCustomer(customer);
        when(this.repairOrderRepository.save((RepairOrder) any())).thenReturn(repairOrder);
        when(this.orderNumberGenerationService.generate()).thenReturn(1L);
        when(this.customerService.findAll()).thenReturn(new ArrayList<Customer>());

        Device device1 = new Device();
        device1.setModel("Model");
        device1.setFailureDescription("Failure Description");
        device1.setId(123L);
        device1.setSerialNumber("42");
        device1.setUuid(UUID.randomUUID());
        device1.setManufacturer("Manufacturer");

        Customer customer1 = new Customer();
        customer1.setLastName("Doe");
        customer1.setEmail("jane.doe@example.org");
        customer1.setTelNumber("42");
        customer1.setId(123L);
        customer1.setUuid(UUID.randomUUID());
        customer1.setFirstName("Jane");

        RepairOrder repairOrder1 = new RepairOrder();
        repairOrder1.setRegistrationDate(LocalDate.ofEpochDay(1L));
        repairOrder1.setDevice(device1);
        repairOrder1.setId(123L);
        repairOrder1.setRegistrationNr(1L);
        repairOrder1.setCustomer(customer1);
        assertSame(repairOrder, this.repairOrderService.saveRepairOrder(repairOrder1));
        verify(this.repairOrderRepository).save((RepairOrder) any());
        verify(this.orderNumberGenerationService).generate();
        verify(this.customerService).findAll();
        assertEquals(1L, repairOrder1.getRegistrationNr().longValue());
        Customer customer2 = repairOrder1.getCustomer();
        assertEquals("Doe", customer2.getLastName());
        assertEquals("Jane", customer2.getFirstName());
        assertEquals("jane.doe@example.org", customer2.getEmail());
        assertEquals("42", customer2.getTelNumber());
    }

    @Test
    void testSaveRepairOrder5() {
        Device device = new Device();
        device.setModel("Model");
        device.setFailureDescription("Failure Description");
        device.setId(123L);
        device.setSerialNumber("42");
        device.setUuid(UUID.randomUUID());
        device.setManufacturer("Manufacturer");

        Customer customer = new Customer();
        customer.setLastName("Doe");
        customer.setEmail("jane.doe@example.org");
        customer.setTelNumber("42");
        customer.setId(123L);
        customer.setUuid(UUID.randomUUID());
        customer.setFirstName("Jane");

        RepairOrder repairOrder = new RepairOrder();
        repairOrder.setRegistrationDate(LocalDate.ofEpochDay(1L));
        repairOrder.setDevice(device);
        repairOrder.setId(123L);
        repairOrder.setRegistrationNr(1L);
        repairOrder.setCustomer(customer);
        when(this.repairOrderRepository.save((RepairOrder) any())).thenReturn(repairOrder);
        when(this.orderNumberGenerationService.generate()).thenReturn(1L);

        Customer customer1 = new Customer();
        customer1.setLastName("Doe");
        customer1.setEmail("jane.doe@example.org");
        customer1.setTelNumber("42");
        customer1.setId(123L);
        customer1.setUuid(UUID.randomUUID());
        customer1.setFirstName("Jane");

        ArrayList<Customer> customerList = new ArrayList<Customer>();
        customerList.add(customer1);
        when(this.customerService.findAll()).thenReturn(customerList);

        Device device1 = new Device();
        device1.setModel("Model");
        device1.setFailureDescription("Failure Description");
        device1.setId(123L);
        device1.setSerialNumber("42");
        device1.setUuid(UUID.randomUUID());
        device1.setManufacturer("Manufacturer");

        Customer customer2 = new Customer();
        customer2.setLastName("Doe");
        customer2.setEmail("jane.doe@example.org");
        customer2.setTelNumber("42");
        customer2.setId(123L);
        customer2.setUuid(UUID.randomUUID());
        customer2.setFirstName("Jane");

        RepairOrder repairOrder1 = new RepairOrder();
        repairOrder1.setRegistrationDate(LocalDate.ofEpochDay(1L));
        repairOrder1.setDevice(device1);
        repairOrder1.setId(123L);
        repairOrder1.setRegistrationNr(1L);
        repairOrder1.setCustomer(customer2);
        assertSame(repairOrder, this.repairOrderService.saveRepairOrder(repairOrder1));
        verify(this.repairOrderRepository).save((RepairOrder) any());
        verify(this.orderNumberGenerationService).generate();
        verify(this.customerService).findAll();
        assertSame(customer1, repairOrder1.getCustomer());
        assertEquals(1L, repairOrder1.getRegistrationNr().longValue());
    }

    @Test
    void testSaveRepairOrder6() {
        Device device = new Device();
        device.setModel("Model");
        device.setFailureDescription("Failure Description");
        device.setId(123L);
        device.setSerialNumber("42");
        device.setUuid(UUID.randomUUID());
        device.setManufacturer("Manufacturer");

        Customer customer = new Customer();
        customer.setLastName("Doe");
        customer.setEmail("jane.doe@example.org");
        customer.setTelNumber("42");
        customer.setId(123L);
        customer.setUuid(UUID.randomUUID());
        customer.setFirstName("Jane");

        RepairOrder repairOrder = new RepairOrder();
        repairOrder.setRegistrationDate(LocalDate.ofEpochDay(1L));
        repairOrder.setDevice(device);
        repairOrder.setId(123L);
        repairOrder.setRegistrationNr(1L);
        repairOrder.setCustomer(customer);
        when(this.repairOrderRepository.save((RepairOrder) any())).thenReturn(repairOrder);
        when(this.orderNumberGenerationService.generate()).thenReturn(1L);

        Customer customer1 = new Customer();
        customer1.setLastName("Doe");
        customer1.setEmail("jane.doe@example.org");
        customer1.setTelNumber("42");
        customer1.setId(123L);
        customer1.setUuid(UUID.randomUUID());
        customer1.setFirstName("Jane");

        Customer customer2 = new Customer();
        customer2.setLastName("Doe");
        customer2.setEmail("jane.doe@example.org");
        customer2.setTelNumber("42");
        customer2.setId(123L);
        customer2.setUuid(UUID.randomUUID());
        customer2.setFirstName("Jane");

        ArrayList<Customer> customerList = new ArrayList<Customer>();
        customerList.add(customer2);
        customerList.add(customer1);
        when(this.customerService.findAll()).thenReturn(customerList);

        Device device1 = new Device();
        device1.setModel("Model");
        device1.setFailureDescription("Failure Description");
        device1.setId(123L);
        device1.setSerialNumber("42");
        device1.setUuid(UUID.randomUUID());
        device1.setManufacturer("Manufacturer");

        Customer customer3 = new Customer();
        customer3.setLastName("Doe");
        customer3.setEmail("jane.doe@example.org");
        customer3.setTelNumber("42");
        customer3.setId(123L);
        customer3.setUuid(UUID.randomUUID());
        customer3.setFirstName("Jane");

        RepairOrder repairOrder1 = new RepairOrder();
        repairOrder1.setRegistrationDate(LocalDate.ofEpochDay(1L));
        repairOrder1.setDevice(device1);
        repairOrder1.setId(123L);
        repairOrder1.setRegistrationNr(1L);
        repairOrder1.setCustomer(customer3);
        assertSame(repairOrder, this.repairOrderService.saveRepairOrder(repairOrder1));
        verify(this.repairOrderRepository).save((RepairOrder) any());
        verify(this.orderNumberGenerationService).generate();
        verify(this.customerService).findAll();
        assertSame(customer2, repairOrder1.getCustomer());
        assertEquals(1L, repairOrder1.getRegistrationNr().longValue());
    }
}


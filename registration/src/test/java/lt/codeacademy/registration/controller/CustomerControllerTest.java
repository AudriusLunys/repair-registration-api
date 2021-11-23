package lt.codeacademy.registration.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import java.util.Optional;

import java.util.UUID;

import lt.codeacademy.registration.model.Customer;
import lt.codeacademy.registration.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CustomerController.class})
@ExtendWith(SpringExtension.class)
class CustomerControllerTest {
    @Autowired
    private CustomerController customerController;

    @MockBean
    private CustomerService customerService;

    @Test
    void testCreateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setLastName("Doe");
        customer.setEmail("jane.doe@example.org");
        customer.setTelNumber("42");
        customer.setId(123L);
        customer.setUuid(UUID.randomUUID());
        customer.setFirstName("Jane");
        when(this.customerService.saveCustomer((Customer) any())).thenReturn(customer);

        Customer customer1 = new Customer();
        customer1.setLastName("Doe");
        customer1.setEmail("jane.doe@example.org");
        customer1.setTelNumber("42");
        customer1.setId(123L);
        customer1.setUuid(UUID.randomUUID());
        customer1.setFirstName("Jane");
        String content = (new ObjectMapper()).writeValueAsString(customer1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testDeleteCustomer() throws Exception {
        doNothing().when(this.customerService).deleteCustomer((UUID) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/customer/{uuid}",
                UUID.randomUUID());
        MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteCustomer2() throws Exception {
        doNothing().when(this.customerService).deleteCustomer((UUID) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/v1/customer/{uuid}",
                UUID.randomUUID());
        deleteResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetCustomerById() throws Exception {
        when(this.customerService.findByUuid((UUID) any())).thenReturn(Optional.<Customer>empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/customer/{uuid}",
                UUID.randomUUID());
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetCustomerList() throws Exception {
        when(this.customerService.findAll()).thenReturn(new ArrayList<Customer>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/customer");
        MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetCustomerList2() throws Exception {
        when(this.customerService.findAll()).thenReturn(new ArrayList<Customer>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/customer");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testUpdateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setLastName("Doe");
        customer.setEmail("jane.doe@example.org");
        customer.setTelNumber("42");
        customer.setId(123L);
        customer.setUuid(UUID.randomUUID());
        customer.setFirstName("Jane");
        when(this.customerService.saveCustomer((Customer) any())).thenReturn(customer);

        Customer customer1 = new Customer();
        customer1.setLastName("Doe");
        customer1.setEmail("jane.doe@example.org");
        customer1.setTelNumber("42");
        customer1.setId(123L);
        customer1.setUuid(UUID.randomUUID());
        customer1.setFirstName("Jane");
        String content = (new ObjectMapper()).writeValueAsString(customer1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/v1/customer/{uuid}", UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.customerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated());
    }
}


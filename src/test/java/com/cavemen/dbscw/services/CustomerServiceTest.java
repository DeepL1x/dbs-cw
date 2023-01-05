package com.cavemen.dbscw.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.cavemen.dbscw.entities.customer.Customer;
import com.cavemen.dbscw.entities.customer.CustomerRepository;
import com.cavemen.dbscw.entities.customer.CustomerService;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
  private static Customer customer1 =
      new Customer(1L,"login1","name1","country1","address1");

  private static Customer customer2 =
      new Customer(2L,"login2","name2","country2","address2");

  @Mock
  CustomerRepository customerRepository;


  private CustomerService customerService;

  @BeforeEach
  void setService() {
    customerService = new CustomerService(customerRepository);
  }

  @Test
  void getAllCustomersTest() {
    when(customerRepository.findAll()).thenReturn(List.of(customer1, customer2));
    assertEquals(List.of(customer1, customer2), customerService.getAllCustomers());
  }

  @Test
  void addCustomerTest() {
    when(customerRepository.save(customer1)).thenReturn(customer1);
    assertEquals(customer1, customerService.addCustomer(new Customer(1L,"login1","name1","country1","address1")));
  }

  @Test
  void getCustomerByIdTest() {
    when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(customer1));
    assertEquals(customer1, customerService.getCustomerById(1L).get());
  }

  @Test
  void updateCustomerTest() {
    when(customerRepository.save(customer1)).thenReturn(customer1);
    assertEquals(customer1, customerService.updateCustomer(new Customer(1L,"login1","name1","country1","address1")));
  }

  @Test
  void getCategoriesTest() {
    when(customerRepository.getCategories(1L)).thenReturn(Set.of("category1","category2"));
    assertEquals(Set.of("category1","category2"), customerService.getCategories(1L));
  }

  @Test
  void deleteCustomerTest() {
    doNothing().when(customerRepository).deleteById(1L);
    assertDoesNotThrow(() -> customerService.deleteCustomer(1L));
  }
}

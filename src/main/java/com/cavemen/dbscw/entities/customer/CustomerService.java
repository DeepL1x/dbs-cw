package com.cavemen.dbscw.entities.customer;

import com.cavemen.dbscw.entities.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void connect(Long customerID, Long categoryID) {
        customerRepository.connect(customerID, categoryID);
    }

    public Optional<Customer> getCustomerById(Long id){
        return customerRepository.findById(id);
    }

    public Set<String> getCategories(Long id) {
        return customerRepository.getCategories(id);
    }

    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }
}

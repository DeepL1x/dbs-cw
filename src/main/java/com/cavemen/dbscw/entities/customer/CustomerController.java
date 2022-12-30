package com.cavemen.dbscw.entities.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Optional<Customer>> getCustomer(@PathVariable("id") Long id){
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping(
        value = "/add",
        consumes = "application/json",
        produces = "application/json"
)
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.OK);
    }

    @PostMapping("/connect/{cust_id}/{cat_id}")
    public void connectToCategory(@PathVariable("cust_id") Long customerID, @PathVariable("cat_id") Long categoryID) {
//        Customer customer = getCustomer(customerID).getBody().get();
//        assert customer != null;
//        customer.addCategory(categoryID);
        /*ResponseEntity res = getCustomer(customerID);
        if (res.hasBody()) {
            Customer customer = (Customer) res.getBody();
            assert customer != null;
            customer.addCategory(categoryID);
        }*/
        customerService.connect(customerID, categoryID);
    }

    @PutMapping(
            value = "/update",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.updateCustomer(customer), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

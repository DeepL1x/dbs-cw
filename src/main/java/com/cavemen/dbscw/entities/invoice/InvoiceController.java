package com.cavemen.dbscw.entities.invoice;


import com.cavemen.dbscw.entities.order.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/invoice")
public class InvoiceController {

    InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Invoice>> getInvoices(){
        return new ResponseEntity<>(invoiceService.getInvoices(), HttpStatus.OK);
    }

    @GetMapping(value ="/getInvoice/{id}")
    public ResponseEntity<Optional<Invoice>> getInvoice(@PathVariable String id){
        return new ResponseEntity<>(invoiceService.getInvoice(id), HttpStatus.OK);
    }

    @PostMapping(value = "/createInvoice/{id}")
    public ResponseEntity<Optional<Invoice>> addItemToOrder(@PathVariable String id){
        return new ResponseEntity<>(invoiceService.createInvoice(id), HttpStatus.OK);
    }

    @PostMapping(value = "/approve/{id}")
    public ResponseEntity<Optional<Invoice>> approveInvoice(@PathVariable String id){
            return new ResponseEntity<>(invoiceService.approveInvoice(id), HttpStatus.OK);
    }

}

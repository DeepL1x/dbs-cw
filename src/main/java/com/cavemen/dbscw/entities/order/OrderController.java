package com.cavemen.dbscw.entities.order;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getItems() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @PostMapping(
            value = "/create",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Order> addArticle(@RequestBody Order order){
        return new ResponseEntity<>(orderService.createOrder(order).get(), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Optional<Order>> getOrderById(@PathVariable("id") String id) {
        return new ResponseEntity<>(orderService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/addItem/{id}")
    public ResponseEntity<Optional<Order>> addItemToOrder(@PathVariable String id, @RequestBody OrderItem customKeyValue){
        return new ResponseEntity<>(orderService.addItem(customKeyValue, id), HttpStatus.OK);
    }
}

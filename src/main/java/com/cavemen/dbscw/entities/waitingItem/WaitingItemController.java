package com.cavemen.dbscw.entities.waitingItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/waitingItem")
public class WaitingItemController {
    private final WaitingItemService waitingItemService;

    @Autowired
    public WaitingItemController(WaitingItemService waitingItemService) {
        this.waitingItemService = waitingItemService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<WaitingItem>> getWaitingItems(){
        return new ResponseEntity<>(waitingItemService.getItems(), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Optional<WaitingItem>> getWaitingItemById(@PathVariable("id") String id){
        return new ResponseEntity<>(waitingItemService.getItemById(id), HttpStatus.OK);
    }

    @PostMapping(
            value = "/add",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<WaitingItem> addWaitingItem(@RequestBody WaitingItem waitingItem) {
        return new ResponseEntity<>(waitingItemService.addItem(waitingItem), HttpStatus.OK);
    }

    @PutMapping(
            value = "/update",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<WaitingItem> updateWaitingItem(@RequestBody WaitingItem waitingItem){
        return new ResponseEntity<>(waitingItemService.updateItem(waitingItem), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReadyItem(@PathVariable("id") String id){
        waitingItemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

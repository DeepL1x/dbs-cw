package com.cavemen.dbscw.entities.readyItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/readyItem")
public class ReadyItemController {
    private final ReadyItemService readyItemService;

    @Autowired
    public ReadyItemController(ReadyItemService readyItemService) {
        this.readyItemService = readyItemService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReadyItem>> getItems() {
        return new ResponseEntity<>(readyItemService.getReadyItems(), HttpStatus.OK);
    }

    @GetMapping("/all/descByPrice")
    public  ResponseEntity<List<ReadyItem>> getItemsDescByPrice(){
        return new ResponseEntity<>(readyItemService.getReadyItemsDescByPrice(), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Optional<ReadyItem>> getItemById(@PathVariable("id") String id){
        return new ResponseEntity<>(readyItemService.getReadyItemById(id), HttpStatus.OK);
    }

    @PostMapping(
            value = "/add",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<ReadyItem> addItem(@RequestBody ReadyItem readyItem) {
        return new ResponseEntity<>(readyItemService.addReadyItem(readyItem), HttpStatus.OK);
    }

    @PutMapping(
            value = "/update",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<ReadyItem> updateItem(@RequestBody ReadyItem readyItem){
        return new ResponseEntity<>(readyItemService.updateReadyItem(readyItem), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable("id") String id){
        readyItemService.deleteReadyItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

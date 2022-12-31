package com.cavemen.dbscw.entities.provider;

import com.cavemen.dbscw.entities.provider.Provider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/providers")
public class ProviderController {
    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Provider>> getAllProviders(){
        return new ResponseEntity<>(providerService.getAllProviders(), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Optional<Provider>> getProvider(@PathVariable("id") Long id){
        return new ResponseEntity<>(providerService.getProviderById(id), HttpStatus.OK);
    }

    @PostMapping(
            value = "/add",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Provider> addProvider(@RequestBody Provider provider) {
        return new ResponseEntity<>(providerService.addProvider(provider), HttpStatus.OK);
    }

    @PostMapping("/connect/{prov_id}/{cat_id}")
    public void connectToCategory(@PathVariable("prov_id") Long providerID, @PathVariable("cat_id") Long categoryID) {
        providerService.connect(providerID, categoryID);
    }

    @PutMapping(
            value = "/update",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Provider> updateProvider(@RequestBody Provider provider){
        return new ResponseEntity<>(providerService.updateProvider(provider), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProvider(@PathVariable("id") Long id){
        providerService.deleteProvider(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
